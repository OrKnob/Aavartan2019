package com.technocracy.app.aavartan.utils;

public class AppConstants {

    public static final String BASE_URL = "http://aavartan.nitrr.ac.in:8000/";
    static final String VALID_EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    static final String VALID_PASSWORD_REGEX = "((?=.*\\d)(?=.*[A-Za-z]).{8,16})";
    static final String APP_SHARED_PREFERENCES = "AppSharedPreferences";
    static final String LOGGED_IN = "logged_in";
    static final String USER_NAME = "user_name";
    static final String USER_TOKEN = "user_token";
    static final String USER_ID = "user_id";
    static final String OTP_VERIFIED = "otp_verified";

    public static final String OTP_VERIFY_SUCCESS = "User verified Successfully";
    public static final String EVENT_REGISTER_SUCCESS = "You are sucessfully Registered";
    public static final String EVENT_REGISTER_VERIFY = "Please Verify first";
    public static final String EVENT_ALREADY_REGISTERED = "You are already registered";
    public static final String INVALID_TOKEN = "Invalid token.";

    public static final String AUTH_INTENT_EXTRA = "auth";
    public static final String AUTH_INTENT_LOGIN = "login";
    public static final String AUTH_INTENT_SIGNUP = "signup";
    public static final String AUTH_INTENT_LOGOUT = "logout";
    public static final String EVENT_INTENT_EXTRA = "event";
    public static final String FORGOT_PASSWORD_INTENT_EXTRA = "email";
    public static final String WEBVIEW_EXTRA = "url";
}
