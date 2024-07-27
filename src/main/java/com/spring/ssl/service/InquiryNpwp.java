package com.spring.ssl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.ssl.model.request.InquiryNPWPJsonRequest;
import com.spring.ssl.model.response.InquiryNPWPJsonResponse;

public interface InquiryNpwp {
    InquiryNPWPJsonResponse findNpwp() throws JsonProcessingException;
}
