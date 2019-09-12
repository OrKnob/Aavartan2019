package com.technocracy.app.aavartan.utils;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPreferencesManager() {
    }

    private static SharedPreferences getInstance() {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getInstance().getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    static void setIsLoggedIn(boolean isLoggedIn) {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    static boolean getIsLoggedIn() {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        return sharedPreferences.getBoolean(AppConstants.LOGGED_IN, false);
    }

    static void setUserName(String userName) {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        editor = sharedPreferences.edit();
        editor.putString(AppConstants.USER_NAME, userName);
        editor.apply();
    }

    static String getUserName() {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        return sharedPreferences.getString(AppConstants.USER_NAME, null);
    }

    static void setUserToken(String userToken) {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        editor = sharedPreferences.edit();
        editor.putString(AppConstants.USER_TOKEN, userToken);
        editor.apply();
    }

    static String getUserToken() {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        return sharedPreferences.getString(AppConstants.USER_TOKEN, null);
    }

    static void setUserID(int userID) {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        editor = sharedPreferences.edit();
        editor.putInt(AppConstants.USER_ID, userID);
        editor.apply();
    }

    static int getUserID() {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        return sharedPreferences.getInt(AppConstants.USER_ID, -1);
    }

    static void setIsNumberVerified(boolean isOTPVerified) {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.OTP_VERIFIED, isOTPVerified);
        editor.apply();
    }

    static boolean getIsNumberVerified() {
        if (sharedPreferences == null)
            sharedPreferences = getInstance();
        return sharedPreferences.getBoolean(AppConstants.OTP_VERIFIED, false);
    }

    static void logout(){
        editor.clear();
        editor.commit();
    }

}