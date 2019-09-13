package com.technocracy.app.aavartan.utils;

import android.text.TextUtils;

public class SessionManager {

    private static String userName,userToken;
    private static int userID = -1;
    private static boolean isLoggedIn = false, isNumberVerified = false;

    public SessionManager() {

    }

    public static boolean getIsLoggedIn() {
        if (!isLoggedIn){
            isLoggedIn = SharedPreferencesManager.getIsLoggedIn();
        }
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        SessionManager.isLoggedIn = isLoggedIn;
        SharedPreferencesManager.setIsLoggedIn(isLoggedIn);
    }

    public static String getUserName() {
        if (TextUtils.isEmpty(userName)) {
            userName = SharedPreferencesManager.getUserName();
        }
        return userName;
    }

    public static void setUserName(String userName) {
        SessionManager.userName = userName;
        SharedPreferencesManager.setUserName(userName);
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

    public static int getUserID() {
        if (userID == -1){
            userID = SharedPreferencesManager.getUserID();
        }
        return userID;
    }

    public static void setUserID(int userID) {
        SessionManager.userID = userID;
        SharedPreferencesManager.setUserID(userID);
    }

    public static boolean getIsNumberVerified() {
        if (!isNumberVerified){
            isNumberVerified = SharedPreferencesManager.getIsNumberVerified();
        }
        return isNumberVerified;
    }

    public static void setIsNumberVerified(boolean isNumberVerified) {
        SessionManager.isNumberVerified = isNumberVerified;
        SharedPreferencesManager.setIsNumberVerified(isNumberVerified);
    }

    public static void logout() {
        SharedPreferencesManager.logout();
        userName = null;
        userToken = null;
        userID = -1;
        isLoggedIn = false;
        isNumberVerified = false;
    }
}