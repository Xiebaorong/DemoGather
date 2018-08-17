package com.example.a7invensun.verifydemo.dialog.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 7invensun on 2018/8/17.
 */

public abstract class BaseDialog {
    public static final int DIALOG_COMMON_STYLE = 0;
    private Context mContext;

    protected abstract int getDialogStyleId();

    private static final String TAG = "BaseDialog";
    private AlertDialog dialog;

    protected abstract View getView(Context mContext);

    private float WIDTH = 0.5f;
    private float HEIGHT = 0.5f;
    private static int DIALOG_X_LOCATION = 0;
    private static int DIALOG_Y_LOCATION = 0;

    public BaseDialog(Context context) {
        this.mContext = context;
        AlertDialog.Builder builder;
        if (getDialogStyleId() == 0) {
            builder = new AlertDialog.Builder(mContext, DIALOG_COMMON_STYLE);
        } else {
            builder = new AlertDialog.Builder(mContext, getDialogStyleId());
        }
        dialog = builder.create();
        dialog.setView(getView(mContext));
    }

    public BaseDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public void show() {
        dialog.show();
        setSize();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public BaseDialog setdismissListeren(DialogInterface.OnDismissListener dismissListener) {
        dialog.setOnDismissListener(dismissListener);
        return this;
    }

    public BaseDialog setDialogSize(float width, float height) {
        WIDTH = width;
        HEIGHT = height;
        return this;
    }

    public BaseDialog setDialogLocation(int x, int y) {
        DIALOG_X_LOCATION = x;
        DIALOG_Y_LOCATION = y;
        return this;
    }

    private void setSize() {
        Window dialogWindow = dialog.getWindow();
        WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        lp.width = (int) (int) (d.getWidth() * WIDTH);
        lp.height = (int) (d.getHeight() * HEIGHT);
        lp.x = DIALOG_X_LOCATION; // 新位置X坐标
        lp.y = DIALOG_Y_LOCATION; // 新位置Y坐标
        dialogWindow.setAttributes(lp);
    }

}
