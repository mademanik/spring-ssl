package com.spring.ssl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ssl.model.request.BodyInquiryNPWPJsonRequest;
import com.spring.ssl.model.request.InquiryNPWPJsonRequest;
import com.spring.ssl.model.request.InquiryNPWPJsonRequest2;
import com.spring.ssl.model.request.SoaHeaderInquiryNPWPJsonRequest;
import com.spring.ssl.model.response.InquiryNPWPJsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.util.Collections;

@Service
@Slf4j
public class InquiryNpwpImpl implements InquiryNpwp{
    @Override
    public InquiryNPWPJsonResponse findNpwp() throws JsonProcessingException {

        //SOA Header Request
        SoaHeaderInquiryNPWPJsonRequest soaHeaderRequest = new SoaHeaderInquiryNPWPJsonRequest();
        soaHeaderRequest.setExternalId("477467");
        soaHeaderRequest.setChannelId("11");
        soaHeaderRequest.setTimestamp("2023-01-27 10:00:40");

        //Body Request
        BodyInquiryNPWPJsonRequest bodyRequest = new BodyInquiryNPWPJsonRequest();
        bodyRequest.setRequestDate("");
        bodyRequest.setBankID("");
        bodyRequest.setBranchID("");
        bodyRequest.setAuthCode("");
        bodyRequest.setNpwp("0609362819301000");
        bodyRequest.setKdMAP(411121);
        bodyRequest.setKjs("100");
        bodyRequest.setTraceId("30004331");

        //InquiryNPWPJsonRequest2
        InquiryNPWPJsonRequest2 inquiryNPWPJsonRequest2 = new InquiryNPWPJsonRequest2();
        inquiryNPWPJsonRequest2.setBody(bodyRequest);
        inquiryNPWPJsonRequest2.setSoaHeader(soaHeaderRequest);

        //InquiryNPWPJsonRequest
        InquiryNPWPJsonRequest request = new InquiryNPWPJsonRequest();
        request.setInquryNPWPRequest(inquiryNPWPJsonRequest2);

        HttpHeaders headers = createHttpHeaders();

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequestString = mapper.writeValueAsString(request);

        HttpEntity<String> entity = new HttpEntity<String>(jsonRequestString, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InquiryNPWPJsonResponse> response = null;
        StringBuilder strErrorLog = new StringBuilder();

        System.out.println("Inquiry npwp json request: ");
        System.out.println(mapper.writeValueAsString(request));

        //ini sangat penting untuk bypass untrusted ssl
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        String url = "https://localhost:8443/api/ubp/inquirynpwp";

        try {
            response = restTemplate.exchange(
                    url, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<InquiryNPWPJsonResponse>() {
                    });
        } catch (HttpStatusCodeException e) {
            strErrorLog.append(e.getResponseBodyAsString().concat(";"));
            log.info("Inquiry npwp HttpStatusCodeException : " + e.getStatusCode() + " => " + e.getResponseBodyAsString());
        }

        InquiryNPWPJsonResponse inquiryNPWPJsonResponse = response.getBody();
        String responseCode = inquiryNPWPJsonResponse.getInquiryNPWPResponse().getBody().getResponseCode();

        if (responseCode.startsWith("00")) {
            log.info("Inquiry NPWP Successfully retrieved: " + responseCode);
            System.out.println("Inquiry NPWP Successfully retrieved: " + responseCode);
        } else {
            log.info("Inquiry NPWP Failed: " + responseCode);
            System.out.println("Inquiry NPWP Successfully retrieved " + responseCode);
        }

        return inquiryNPWPJsonResponse;
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
