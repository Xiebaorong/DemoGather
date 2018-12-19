package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback;

public interface ObserverOnNextListener<T> {
    void onNext(T t);

    void onError(Throwable e);
}
