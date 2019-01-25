package com.example.zhongdun.verifydemo.rx_rt.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String TAG = "RetrofitFactory";


    public static Retrofit createGsonWithRxJavaRetrofit(String baseUrl, OkHttpClient okHttpClient) {
        return create(baseUrl, okHttpClient, GsonConverterFactory.create(), RxJava2CallAdapterFactory.create());
    }

    private static Retrofit create(String baseUrl, OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
        return retrofit;
    }
}
