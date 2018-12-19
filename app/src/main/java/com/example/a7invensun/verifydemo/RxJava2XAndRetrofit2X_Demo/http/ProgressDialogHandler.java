package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback.ProgressCancelListener;

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private Context context;
    private ProgressDialog dialog;
    private boolean isCancelable;
    private ProgressCancelListener mProgressCancelListener;

    public <T> ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean isCancelable) {
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.isCancelable = isCancelable;
    }

    private void initProgressDialog() {
        if (dialog == null)
            dialog = new ProgressDialog(context);

        dialog.setCancelable(isCancelable);

        if (isCancelable) {
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mProgressCancelListener.onCancelProgress();
                }
            });
        }

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
