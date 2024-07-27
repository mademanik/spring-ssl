package com.spring.ssl.model.request;

import lombok.Data;

@Data
public class BodyInquiryNPWPJsonRequest {
    private String requestDate;
    private String bankID;
    private String branchID;
    private String authCode;
    private String npwp;
    private Integer kdMAP;
    private String kjs;
    private String traceId;
}
