package com.example.a7invensun.verifydemo;

import android.app.Application;

/**
 * Created by 7invensun on 2018/8/24.
 */

public class BaseApp extends Application{
    private static BaseApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApp getInstance(){
        return instance;
    }

}
