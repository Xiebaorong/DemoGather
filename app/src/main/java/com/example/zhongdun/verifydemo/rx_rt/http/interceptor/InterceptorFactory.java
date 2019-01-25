package com.example.zhongdun.verifydemo.rx_rt.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorFactory {
    private static final String TAG = "InterceptorFactory";

    /**
     * HttpLoggingInterceptor消息拦截器  NONE,BASIC,HEADER,BODY
     * 1.BASEIC:请求/响应行 2.HEADER:请求/响应行 + 头 3.BODY:请求/响应航 + 头 + 体
     *
     * @param isDebug
     * @return
     */
    public static HttpLoggingInterceptor setHttpLoggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, "log: " + message);
            }
        });

        if (isDebug) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return loggingInterceptor;
    }

    public static Interceptor setInterceptor(final boolean isDebug) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = null;
                MediaType mediaType = null;
                String json = null;
                try {
                    if (isDebug) {
                        Log.i(TAG, "Request :" + request.toString());
                    }

                    response = chain.proceed(request);
                    mediaType = response.body().contentType();
                    json = response.body().string();

                    if (isDebug) {
                        Log.i(TAG, "Request: URL \n" + request.url() + "\nResponse : " + json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response.newBuilder().body(ResponseBody.create(mediaType, json)).build();
            }
        };

        return interceptor;
    }
}
