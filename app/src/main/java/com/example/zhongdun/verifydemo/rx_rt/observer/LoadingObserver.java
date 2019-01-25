package com.example.zhongdun.verifydemo.rx_rt.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoadingObserver<T> implements Observer<T> {
    private ObserverListener listener;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
            listener.onSuccess(t);

    }

    @Override
    public void onError(Throwable e) {
        listener.onError(e);
    }

    @Override
    public void onComplete() {

    }
}
