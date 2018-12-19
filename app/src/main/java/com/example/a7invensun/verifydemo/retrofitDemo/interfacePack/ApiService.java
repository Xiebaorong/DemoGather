package com.example.a7invensun.verifydemo.retrofitDemo.interfacePack;

import com.example.a7invensun.verifydemo.retrofitDemo.bean.PoetryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //https://api.apiopen.top/recommendPoetry
    @GET("recommendPoetry")
    Observable<PoetryModel> findPoetryModel();
}
