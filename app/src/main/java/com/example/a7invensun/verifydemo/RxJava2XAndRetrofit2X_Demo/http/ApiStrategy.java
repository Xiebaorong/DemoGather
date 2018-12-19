package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import android.text.TextUtils;
import android.util.Log;

import com.example.a7invensun.verifydemo.BaseApp;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.apiservice.ApiService;
import com.example.a7invensun.verifydemo.httpWeather.util.HttpUtils;
import com.example.a7invensun.verifydemo.mvpDemo.view.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiStrategy {
    private static final String TAG = "ApiStrategy";
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7;

    private static ApiStrategy instance;

    public static ApiStrategy getInstance() {
        if (instance == null) {
            synchronized (ApiStrategy.class) {
                if (instance == null) instance = new ApiStrategy();
            }
        }
        return instance;
    }

    public ApiService getApiService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "cache");
        final Cache cache = new Cache(cacheFile, 1024 * 1024 * 1000);
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        //.addHeader().......
                        .build();
                return chain.proceed(request);
            }
        };

        OkHttpClient build = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(URLUtils.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;

    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            //if (!Ne)没有网络
            if (!HttpUtils.isNetworkConnected(MyApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Log.e(TAG, "intercept: 1"  );
            Response response = chain.proceed(request);
            Log.e(TAG, "intercept: 2"  );
            //有网
            if (HttpUtils.isNetworkConnected(MyApplication.getInstance())) {
                Log.e(TAG, "intercept: 3"  );
                int maxAge = 30;
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();

            } else {
                Log.e(TAG, "intercept: 4"  );
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }

        }
    };
}
