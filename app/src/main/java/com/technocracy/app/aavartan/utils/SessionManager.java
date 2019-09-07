package com.technocracy.app.aavartan.utils;

import android.text.TextUtils;

public class SessionManager {
    private static String userToken;

    public SessionManager(){

    }

    public static String getUserToken() {
        if (TextUtils.isEmpty(userToken)) {
            userToken = SharedPreferencesManager.getUserToken();
        }
        return userToken;
    }

    public static void setUserToken(String userToken) {
        SessionManager.userToken = userToken;
        SharedPreferencesManager.setUserToken(userToken);
    }

}