package com.example.a7invensun.verifydemo.httpWeather.util;

import android.util.Log;

import com.example.a7invensun.verifydemo.BaseApp;
import com.example.a7invensun.verifydemo.httpWeather.callBack.BaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 7invensun on 2018/8/23.
 */

public class OkHttpManager {
    private static final String TAG = "OkHttpManager";
    private static final long DEFAULT_DIR_CACHE = 50 * 1024 * 1024;
    private static OkHttpManager instance;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                instance = new OkHttpManager();
            }
        }
        return instance;
    }

    public OkHttpManager() {
        File cacheFile = new File(BaseApp.getInstance().getExternalCacheDir(), "DemoCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(10, TimeUnit.SECONDS)//超时时间15S
                 .addInterceptor(new NetInterceptorUtil())
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        //.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
        mGson = new Gson();
    }

    public void getRequest(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String json = response.body().string();
                    Log.e(TAG, "onResponse: " + json);
                }

            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new LogInterceptor()).build();
//                Request request = new Request.Builder()
//                        .url("http://www.taobao.com")
//                        .get()
//                        .build();
//                Call call = okHttpClient.newCall(request);
//                try {
//                    call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    /**
     * post未封装
     *
     * @param url
     * @param params
     */
    public void poseRequest(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("platform", "Android")
                .add("version", "1.0")
                .add("key", "123456");
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                builder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        FormBody body = builder.build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.code());
                if (response.code() == 200) {
                    String json = response.body().string();
                    Log.e(TAG, "onResponse: " + json);
                }
            }
        });
    }


    public void newGetRequest(String url, Map<String, String> params, BaseCallBack callBack) {
        Request request = buildRequest(url, params, HttpMethodType.GET);
        doRequest(request, callBack);
    }

    public void newPostRequest(String url, Map<String, String> params, BaseCallBack callBack) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        doRequest(request, callBack);
    }


    private Request buildRequest(String url, Map<String, String> params, HttpMethodType httpMethodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (httpMethodType == HttpMethodType.GET) {
            builder.get();
        } else if (httpMethodType == HttpMethodType.POST) {
            RequestBody requestBody = buildFormData(params);
            builder.post(requestBody);
        }
        return builder.build();
    }

    private void doRequest(final Request request, final BaseCallBack callBack) {
        callBack.OnRequestBefore(request);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onResponse(response);
                String result = response.body().string();
                if (response.isSuccessful()) {
                    if (callBack.mType == String.class) {
                        callBackSuccess(callBack, call, response, result);
                    } else {
                        try {
                            Object object = mGson.fromJson(result, callBack.mType);
                            callBackSuccess(callBack, call, response, object);
                        } catch (JsonSyntaxException e) {
                            callBack.onError(call, response.code(), e);
                        }
                    }
                } else {
                    callBack.onError(call, response.code(), null);
                }
            }
        });
    }

    private void callBackSuccess(BaseCallBack callBack, Call call, Response response, Object result) {
        callBack.onSuccess(call, response, result);
    }

    private RequestBody buildFormData(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("platform", "Android")
                .add("version", "1.0")
                .add("key", "123456");
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                builder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return builder.build();
    }


    enum HttpMethodType {
        GET, POST
    }
}
