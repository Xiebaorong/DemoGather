package com.example.zhongdun.verifydemo.rx_rt.apiservice;


import com.example.zhongdun.verifydemo.rx_rt.model.PoetryModel;
import com.example.zhongdun.verifydemo.rx_rt.model.SinglePoetryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //https://api.apiopen.top/recommendPoetry
    @GET("recommendPoetry")
    Observable<PoetryModel> getPoerty();

    //https://api.apiopen.top/singlePoetry
    @GET("singlePoetry")
    Observable<SinglePoetryModel> getSinglePoetry();

}
