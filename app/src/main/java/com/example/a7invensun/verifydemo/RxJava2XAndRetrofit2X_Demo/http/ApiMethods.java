package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.PoetryModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {
    public static void ApiSubscibe(Observable observable , Observer observer){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getPoetry(Observer<PoetryModel> observer, int page, int count){
        ApiSubscibe(Api.getInstance().getApiService().getTopMovice(page,count),observer);
    }

    public static void getStrategyPoetry(Observer<PoetryModel> observer, int page, int count){
        ApiSubscibe(ApiStrategy.getInstance().getApiService().getTopMovice(page,count),observer);
    }
}
