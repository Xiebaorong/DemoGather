package com.example.zhongdun.verifydemo.rx_rt.http;

import com.example.a7invensun.verifydemo.BuildConfig;
import com.example.zhongdun.verifydemo.rx_rt.http.interceptor.InterceptorFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpFactory {
    private static final String TAG = "OkHttpFactory";
    public static final long READ_TIMEOUT = 5;
    public static final long CONNECT_TIMEOUT = 5;

    public static OkHttpClient create() {
        return create(BuildConfig.DEBUG);
    }

    public static OkHttpClient create(boolean isDebug) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorFactory.setHttpLoggingInterceptor(isDebug))
                .addInterceptor(InterceptorFactory.setInterceptor(isDebug))
                .build();
        return client;
    }
}
