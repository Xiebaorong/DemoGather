package com.example.a7invensun.verifydemo.httpWeather.util;

import com.example.a7invensun.verifydemo.BaseApp;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 7invensun on 2018/8/24.
 */

public class NetInterceptorUtil implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

//        if (!HttpUtils.isNetworkConnected(BaseApp.getInstance())) {
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//        }
        Response response = chain.proceed(request);
//        if (HttpUtils.isNetworkConnected(BaseApp.getInstance())) {
//            int maxAge = 30;
//            // 有网络时 设置缓存超时时间30s
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
//
//        }else {
//            int maxStale = 60 * 60 * 24 * 28;
//            response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .removeHeader("Pragma")
//                    .build();
//        }

        return response;
    }
}
