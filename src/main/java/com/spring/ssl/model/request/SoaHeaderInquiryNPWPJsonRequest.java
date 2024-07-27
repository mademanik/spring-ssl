package com.spring.ssl.model.request;

import lombok.Data;

@Data
public class SoaHeaderInquiryNPWPJsonRequest {
    private String externalId;
    private String channelId;
    private String timestamp;
}
