package com.example.a7invensun.verifydemo.retrofitDemo.interfacePack;

import com.example.a7invensun.verifydemo.retrofitDemo.bean.Translation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 7invensun on 2018/8/27.
 */

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=interface%20World")
    Call<Translation> getCall();
}
