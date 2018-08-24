package com.example.a7invensun.verifydemo.httpWeather.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 7invensun on 2018/8/23.
 */

public class LogInterceptor implements Interceptor {
    private static final String TAG = "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e(TAG, "intercept: "+ request );
        Response response = chain.proceed(request);
        Log.e(TAG, "intercept: "+ response );


        return response;
    }
}
