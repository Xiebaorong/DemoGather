package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import android.content.Context;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.Rx_ReActivity;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback.ObserverOnNextListener;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback.ProgressCancelListener;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.PoetryModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private ObserverOnNextListener listener;
    private Context context;
    private ProgressDialogHandler progressDialogHandler;
    private Disposable d;

    public ProgressObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        progressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (progressDialogHandler != null) {
            progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (progressDialogHandler != null) {
//            progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG));
            progressDialogHandler = null;
        }
    }

    @Override
    public void onCancelProgress() {
        if (!d.isDisposed()) {
            d.dispose();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.onError(e);
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }
}
