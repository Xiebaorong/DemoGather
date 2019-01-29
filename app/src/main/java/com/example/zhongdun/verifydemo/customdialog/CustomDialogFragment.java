package com.example.zhongdun.verifydemo.customdialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class CustomDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View customView = onCreateDialogView();
        dialog.setContentView(customView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowParams = dialog.getWindow().getAttributes();
        windowParams.width = (int) (getResources().getDisplayMetrics().widthPixels - getResources().getDisplayMetrics().density * 20);
        dialog.getWindow().setAttributes(windowParams);
        return dialog;

    }

    public  abstract View onCreateDialogView();
}
