package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import android.content.Context;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.Rx_ReActivity;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback.ObserverOnNextListener;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.PoetryModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver<T> implements Observer<T> {
    private ObserverOnNextListener listener;
    private Context context;

    public MyObserver(Context context, ObserverOnNextListener<T> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.onError(e);
    }

    @Override
    public void onComplete() {

    }
}
