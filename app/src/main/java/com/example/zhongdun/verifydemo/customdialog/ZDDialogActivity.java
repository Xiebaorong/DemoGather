package com.example.zhongdun.verifydemo.customdialog;

import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;
import com.example.zhongdun.verifydemo.customdialog.notification.CustomNotificationDialog;
import com.example.zhongdun.verifydemo.customdialog.notification.MyDialogFragment;

public class ZDDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zddialog);

    }

    public void zd_dialog_show(View view) {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用
        Point outSize = new Point();
        display.getSize(outSize);
        new CustomNotificationDialog(this, outSize,R.style.dialog, "你确定删除吗?", new CustomNotificationDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Toast.makeText(ZDDialogActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        }).setTitle("提示").show();
    }



    public void zd_fragment_dialog_show(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "FirstDialog");
    }
}
