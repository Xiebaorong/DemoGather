package com.example.a7invensun.verifydemo.lockscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.lockscreen.util.SildingFinishLayout;

public class LockScreenActivity extends AppCompatActivity {
    private static final String TAG = "LockScreenActivity";
    private SildingFinishLayout sfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        setContentView(R.layout.activity_lock_screen);
        initView();
    }
    private void initView() {
        sfl = findViewById(R.id.sfl);
        sfl.setBackgroundResource(R.color.colorAccent);//设置了锁屏页面的背景色，根据自己的需要进行设置
        sfl.setEnableLeftSildeEvent(true);
        sfl.setEnableRightSildeEvent(false);
        sfl.setOnSildingFinishListener(new OnSildingFinishListenerImplementation());
    }
    /**
     * 左右滑动的监听
     */
    public final class OnSildingFinishListenerImplementation implements
            SildingFinishLayout.OnSildingFinishListener {
        @Override
        public void onSildingForward() {
            LockScreenActivity.this.finish();
        }

        @Override
        public void onSildingBack() {
            LockScreenActivity.this.finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_BACK: {
                return true;
            }
            case KeyEvent.KEYCODE_MENU: {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }
}
