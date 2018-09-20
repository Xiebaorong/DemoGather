package com.example.a7invensun.verifydemo.mvpDemo.view;

import android.app.Application;

import com.example.a7invensun.verifydemo.mvpDemo.util.OkhttpUtil;
import com.example.a7invensun.verifydemo.mvpDemo.util.SharedPreferencesUtil;


public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesUtil.getInstance().initSharedPreferences(instance);
        OkhttpUtil.getInstance().initOkHttp();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
