package com.ighan.core.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class ApiSharedPreferences {

    private static ApiSharedPreferences instance;

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    private ApiSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(ApiSharedPreferences.class.getName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        editor.commit();
    }

    public static ApiSharedPreferences getInstance(Context context) {
        if (instance == null)
            instance = new ApiSharedPreferences(context);
        return instance;
    }

    public void setToken(String token) {
        editor.putString("token", token).apply();
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void setRefreshToken(String refreshToken) {
        editor.putString("refreshToken", refreshToken).apply();
        editor.commit();
    }

    public String getRefreshToken() {
        return sharedPreferences.getString("refreshToken", null);
    }
}
