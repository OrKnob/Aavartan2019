package com.technocracy.app.aavartan.utils;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesManager {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPreferencesManager() {
    }

    private static SharedPreferences getInstance() {
        if (mSharedPreferences == null) {
            mSharedPreferences = BaseApplication.getInstance().getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    static void setUserToken(String userToken) {
        if (mSharedPreferences == null)
            mSharedPreferences = getInstance();
        editor = mSharedPreferences.edit();
        editor.putString(AppConstants.USER_TOKEN, userToken);
        editor.apply();
    }

    static String getUserToken() {
        if (mSharedPreferences == null)
            mSharedPreferences = getInstance();

        return mSharedPreferences.getString(AppConstants.USER_TOKEN, null);
    }

}