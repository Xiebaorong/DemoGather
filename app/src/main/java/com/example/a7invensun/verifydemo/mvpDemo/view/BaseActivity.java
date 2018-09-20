package com.example.a7invensun.verifydemo.mvpDemo.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 7invensun on 2018/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final int MSG_WHAT_SHOWTOAST = 1;
    public Context mContext;
    private Toast toast;
    private Unbinder bind;
    private Handler baseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WHAT_SHOWTOAST:
                    String content = msg.getData().getString("content");
                    showToast(content);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        bind = ButterKnife.bind(this);
        OnActCreate(savedInstanceState);
        initView();
    }


    public abstract int getLayout();

    protected abstract void OnActCreate(Bundle savedInstanceState);

    protected abstract void initView();

    public void sendToast(String content) {
        Message msg = baseHandler.obtainMessage();
        msg.what = MSG_WHAT_SHOWTOAST;
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }

    private void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(mContext, content, toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
        baseHandler.removeCallbacksAndMessages(1);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
