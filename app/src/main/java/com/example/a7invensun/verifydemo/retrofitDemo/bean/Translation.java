package com.example.a7invensun.verifydemo.retrofitDemo.bean;

import android.util.Log;

/**
 * Created by 7invensun on 2018/8/27.
 */

public class Translation {
    private static final String TAG = "Translation";
    private int status;
    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    public void show() {
        Log.e(TAG, "show--status: " + status);
        Log.e(TAG, "show--from: " + content.from);
        Log.e(TAG, "show--to :" + content.to);
        Log.e(TAG, "show--vendor:" + content.vendor);
        Log.e(TAG, "show--out :" + content.out);
        Log.e(TAG, "show--errNo :" + content.errNo);

    }
}
