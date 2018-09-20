package com.example.a7invensun.verifydemo.mvpDemo.util;


import com.example.a7invensun.verifydemo.mvpDemo.callBack.BaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 7invensun on 2018/9/20.
 */

public class OkhttpUtil {
    private static final String TAG = "OkhttpUtil";
    private static OkhttpUtil instance;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("audio/mp3");
    public static OkhttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkhttpUtil.class) {
                instance = new OkhttpUtil();
            }
        }
        return instance;
    }

    public void initOkHttp() {
        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(10, TimeUnit.SECONDS)//超时时间15S
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        mGson = new Gson();
    }

    public void newRequest(String url, HttpMethodType httpMethodType, Map<String, String> params, BaseCallBack callBack) {
        Request request = buildRequest(url, params, httpMethodType);
        doRequest(request, callBack);
    }

    /**
     * 文件传递
     * @param request
     * @param callBack
     */
//    public void postFileRequest(String url ,SosMsg.ResultBean sosMsg,File file,final BaseCallBack callBack){
//        Request request = FileRequest(url, sosMsg, HttpMethodType.POST,file);
//        doRequest(request,callBack);
//    }


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

    /**
     * 根据状态选择是否传递文件
     * @param url
     * @param sosMsg
     * @param post
     * @param file
     * @return
     */
//    private Request FileRequest(String url, SosMsg.ResultBean sosMsg, HttpMethodType post, File file) {
//        String json = mGson.toJson(sosMsg);
//        RequestBody multipartBody = null;
//        Request.Builder builder = new Request.Builder()
//                .url(url);
//        if (sosMsg.getZt().equals("97")||sosMsg.getZt().equals("99")){
//            multipartBody= new MultipartBody.Builder()
//                    .addFormDataPart("sosMsg",json)
//                    .build();
//        }else if(sosMsg.getZt().equals("98")){
//            RequestBody fileBody=RequestBody.create(MEDIA_TYPE_MARKDOWN,file);
//            multipartBody= new MultipartBody.Builder()
//                    .addFormDataPart("file",file.getName(),fileBody)
//                    .addFormDataPart("sosMsg",json)
//                    .build();
//        }
//        builder.post(multipartBody);
//        return builder.build() ;
//    }


    private Request buildRequest(String url, Map<String, String> params, HttpMethodType httpMethodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (HttpMethodType.GET == httpMethodType) {
            builder.get();
        } else if (HttpMethodType.POST == httpMethodType) {
            RequestBody requestBody = buildFormData(params);
            builder.post(requestBody);
        }
        return builder.build();
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

    private void callBackSuccess(BaseCallBack callBack, Call call, Response response, Object result) {
        callBack.onSuccess(call, response, result);
    }

    public enum HttpMethodType {
        GET, POST
    }

}