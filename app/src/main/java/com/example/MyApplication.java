package com.example;

import android.app.Application;

import com.example.a7invensun.verifydemo.mvpDemo.util.OkhttpUtil;
import com.example.a7invensun.verifydemo.mvpDemo.util.SharedPreferencesUtil;
import com.facebook.drawee.backends.pipeline.Fresco;


public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesUtil.getInstance().initSharedPreferences(instance);
//        OkhttpUtil.getInstance().initOkHttp();
        Fresco.initialize(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
