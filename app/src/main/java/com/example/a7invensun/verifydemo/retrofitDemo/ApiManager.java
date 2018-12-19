package com.example.a7invensun.verifydemo.retrofitDemo;

import com.example.a7invensun.verifydemo.retrofitDemo.interfacePack.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private ApiService apiService;
    private static ApiManager instance;

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) instance = new ApiManager();
            }
        }
        return instance;
    }

    public ApiService getApiService() {
        if (apiService == null) {
            OkHttpClient build = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConstants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
              apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
