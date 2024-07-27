package com.spring.ssl.model.response;

import lombok.Data;

@Data
public class BodyInquiryNPWPJsonResponse {
    private String requestDate;
    private String bankID;
    private String branchID;
    private String npwp;
    private String namaWP;
    private String alamatWP;
    private String namaKdMap;
    private String namaKjs;
    private String traceId;
    private String responseCode;
}
