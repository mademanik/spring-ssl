package com.spring.ssl.model.response;

import lombok.Data;

@Data
public class SoaHeaderInquiryNPWPJsonResponse {
    private String externalId;
    private String channelId;
    private String timestamp;
    private String responseCode;
    private String responseMessage;
    private String responseTimestamp;
    private String errorNumber;
}
