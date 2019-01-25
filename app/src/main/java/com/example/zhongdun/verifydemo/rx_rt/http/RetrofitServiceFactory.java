package com.example.zhongdun.verifydemo.rx_rt.http;

import com.example.zhongdun.verifydemo.rx_rt.util.ApiUtils;

import retrofit2.Retrofit;

public class RetrofitServiceFactory {
    private static final String TAG = "RetrofitServiceFactory";
    private static RetrofitServiceFactory instance;
    private Retrofit retrofit;

    public static RetrofitServiceFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitServiceFactory.class) {
                if (instance == null) instance = new RetrofitServiceFactory();
            }
        }
        return instance;
    }

    public void initRetrofit() {
        retrofit = RetrofitFactory.createGsonWithRxJavaRetrofit(ApiUtils.BASE_URL, OkHttpFactory.create());
    }

    public <T> T createService(Class<T> service) {
//        ApiService apiService = retrofit.create(ApiService.class);
        return retrofit.create(service);
    }

}
