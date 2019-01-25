package com.example.zhongdun.verifydemo.rx_rt.http;

import com.example.zhongdun.verifydemo.rx_rt.apiservice.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaFactory {
    private static void apiSubscibe(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getPoertyStrategy(Observer observer) {
        apiSubscibe(RetrofitServiceFactory.getInstance().createService(ApiService.class).getPoerty(), observer);
    }

    public static void getSinglePoetryStrategy(Observer observer) {
        apiSubscibe(RetrofitServiceFactory.getInstance().createService(ApiService.class).getSinglePoetry(), observer);
    }

}
