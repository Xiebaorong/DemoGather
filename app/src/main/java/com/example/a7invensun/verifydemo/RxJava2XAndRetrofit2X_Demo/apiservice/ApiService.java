package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.apiservice;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.PoetryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //https://api.apiopen.top/getSongPoetry?page=1&count=2
    @GET("getSongPoetry")
    Observable<PoetryModel> getTopMovice(@Query("page") int page , @Query("count") int count);
}
