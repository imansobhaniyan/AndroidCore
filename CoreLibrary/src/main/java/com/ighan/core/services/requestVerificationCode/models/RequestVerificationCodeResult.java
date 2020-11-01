package com.ighan.core.services.requestVerificationCode.models;

public class RequestVerificationCodeResult {

    private String code;

    private boolean exists;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
