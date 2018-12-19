package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.apiservice.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api instance;

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) instance = new Api();
            }
        }
        return instance;
    }

    public ApiService getApiService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLUtils.BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;

    }
}
