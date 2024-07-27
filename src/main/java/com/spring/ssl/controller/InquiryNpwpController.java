package com.spring.ssl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.ssl.model.response.InquiryNPWPJsonResponse;
import com.spring.ssl.service.InquiryNpwp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inquiry-npwp")
public class InquiryNpwpController {

    @Autowired
    private InquiryNpwp inquiryNpwp;

    @GetMapping
    private ResponseEntity<InquiryNPWPJsonResponse> findNpwp() throws JsonProcessingException {
        InquiryNPWPJsonResponse response = inquiryNpwp.findNpwp();
        return ResponseEntity.ok(response);
    }
}
