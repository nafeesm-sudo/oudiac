package com.app.oudiac.dtos.emailDto;

import lombok.Data;

@Data
public class OtpData {
    private String otp;
    private Long expiry;

    public OtpData(String otp, long expiry) {
        this.otp=otp;
        this.expiry=expiry;
    }
}
