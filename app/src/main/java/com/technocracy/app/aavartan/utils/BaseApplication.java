package com.technocracy.app.aavartan.utils;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
