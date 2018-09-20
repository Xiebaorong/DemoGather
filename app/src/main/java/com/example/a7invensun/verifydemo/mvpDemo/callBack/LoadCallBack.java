package com.example.a7invensun.verifydemo.mvpDemo.callBack;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 7invensun on 2018/8/23.
 */

public abstract class LoadCallBack<T> extends BaseCallBack<T> {
    private Context mContext;

    public LoadCallBack(Context context) {
        this.mContext = context;
    }

    @Override
    public void OnRequestBefore(Request request) {

    }

    @Override
    public void onFailure(Call call,IOException e) {

    }

    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void inProgress(int progress, long total, int id) {

    }
}
