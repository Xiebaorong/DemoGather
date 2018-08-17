package com.example.a7invensun.verifydemo.dialog.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class NewInputAttributeDialog extends AlertDialog {
    private Context mContext;

    public NewInputAttributeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
//        setSize();
    }

    private void setSize() {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(150, 150, 150, 150);
        getWindow().setAttributes(layoutParams);


    }

}

