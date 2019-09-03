package com.technocracy.app.aavartan.utils;

public class ValidationManager {

    private ValidationManager() {
    }

    public static boolean isFieldEmpty(String data) {
        boolean isFieldEmpty = false;
        if (data == null || data.equals("")) {
            isFieldEmpty = true;
        }

        return isFieldEmpty;
    }

    public static boolean isEmailValid(String email) {

        String emailRegex = AppConstants.VALID_EMAIL_REGEX;
        boolean isFieldValid = true;

        if (!email.isEmpty() && !email.matches(emailRegex)) {
            isFieldValid = false;
        }

        return !isFieldValid;
    }

    public static boolean isValidMobileNumber(String mobileNumber) {

        boolean isFieldValid = true;

        if (mobileNumber.length() < 10) {
            isFieldValid = false;
        }

        return isFieldValid;
    }

    public static boolean isValidPassword(String password){

        String passwordRegex = AppConstants.VALID_PASSWORD_REGEX;
        boolean isFieldValid = true;

        if (!password.isEmpty() && !password.matches(passwordRegex)) {
            isFieldValid = false;
        }

        return !isFieldValid;
    }

}
