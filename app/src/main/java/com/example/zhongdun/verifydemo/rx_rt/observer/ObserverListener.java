package com.example.zhongdun.verifydemo.rx_rt.observer;

public interface ObserverListener<T> {
    void onSuccess(T t);

    void onError(Throwable e);

}
