package com.ighan.core.services.refreshToken.models;

public class RefreshTokenModel {

    private String refreshToken;

    public RefreshTokenModel(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
