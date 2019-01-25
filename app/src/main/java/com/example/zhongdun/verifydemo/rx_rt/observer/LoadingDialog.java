package com.example.zhongdun.verifydemo.rx_rt.observer;

import android.content.Context;
import android.widget.ProgressBar;

public class LoadingDialog {
    private Context mContext;
    private boolean mIsCancelable;
    private ProgressBar progressBar;

    public LoadingDialog(Context context, boolean isCancelable) {
        mContext = context;
        mIsCancelable = isCancelable;
    }

    private void initProgressDialog() {
        if (progressBar == null) {
            progressBar = new ProgressBar(mContext);

        }
    }
}
