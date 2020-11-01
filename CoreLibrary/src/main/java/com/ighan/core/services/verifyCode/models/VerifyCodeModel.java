package com.ighan.core.services.verifyCode.models;

public class VerifyCodeModel {

    private String verificationCode;

    public VerifyCodeModel(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
