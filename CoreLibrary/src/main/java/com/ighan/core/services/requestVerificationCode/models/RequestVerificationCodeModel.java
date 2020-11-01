package com.ighan.core.services.requestVerificationCode.models;

public class RequestVerificationCodeModel {

    private String phoneNumber;

    public RequestVerificationCodeModel(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
