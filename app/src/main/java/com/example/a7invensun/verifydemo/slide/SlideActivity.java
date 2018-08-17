package com.example.a7invensun.verifydemo.slide;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.lockscreen.util.SildingFinishLayout;

public class SlideActivity extends AppCompatActivity {
    private static final String TAG = "SlideActivity";
    SildingFinishLayout relative;
    /**
     * 滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 临时存储X坐标
     */
    private int tempX;
    /**
     * 按下点的X坐标
     */
    private int downX;
    /**
     * 按下点的Y坐标
     */
    private int downY;
    /**
     * 记录是否正在滑动
     */
    private boolean isSilding;
    /**
     * SildingFinishLayout布局的父布局
     */
    private ViewGroup mParentView;
    private int lastX, lastY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        relative = findViewById(R.id.relative);
        relative.setEnableLeftSildeEvent(true);
        relative.setEnableRightSildeEvent(false);
        relative.setOnSildingFinishListener(new OnSildingFinishListenerImplementation());
    }
    /**
     * 左右滑动的监听
     */
    private final class OnSildingFinishListenerImplementation implements
            SildingFinishLayout.OnSildingFinishListener {
        @Override
        public void onSildingForward() {
            SlideActivity.this.finish();
        }

        @Override
        public void onSildingBack() {
            SlideActivity.this.finish();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
