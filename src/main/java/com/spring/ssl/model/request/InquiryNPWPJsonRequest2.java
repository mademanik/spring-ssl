package com.spring.ssl.model.request;

import lombok.Data;

@Data
public class InquiryNPWPJsonRequest2 {
    private SoaHeaderInquiryNPWPJsonRequest soaHeader;
    private BodyInquiryNPWPJsonRequest body;
}
