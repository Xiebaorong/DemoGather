package com.example.a7invensun.verifydemo.lockscreen.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class SildingFinishLayout extends RelativeLayout {
    private static final String TAG = "SildingFinishLayout";
    /**
     * 滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 滑动类
     */
    private Scroller mScroller;
    /**
     * SildingFinishLayout布局的父布局
     */
    private ViewGroup mParentView;
    /**
     * SildingFinishLayout的宽度
     */
    private int viewWidth;
    /**
     * 按下点的X坐标
     */
    private int downX;
    /**
     * 按下点的Y坐标
     */
    private int downY;
    /**
     * 临时存储X坐标
     */
    private int tempX;
    /**
     * 记录是否正在滑动
     */
    private boolean isSilding;

    private int size; //按下时范围(处于这个范围内就启用切换事件，目的是使当用户从左右边界点击时才响应)
    private boolean enableLeftSildeEvent = true; //是否开启左侧切换事件
    private boolean enableRightSildeEvent = true; // 是否开启右侧切换事件
    private OnSildingFinishListener onSildingFinishListener;
    private boolean isIntercept = false; //是否拦截触摸事件
    private boolean canSwitch;//是否可切换
    private boolean isSwitchFromLeft = false; //左侧切换
    private boolean isSwitchFromRight = false; //右侧侧切换

    public SildingFinishLayout(Context context) {
        super(context);
        init(context);
    }

    public SildingFinishLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public SildingFinishLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        Log.w(TAG, "init: 设备的最小滑动距离" + mTouchSlop);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            mParentView = (ViewGroup) this.getParent();
            viewWidth = this.getWidth();
            size = viewWidth;
        }
        Log.w(TAG, "viewWidth=" + viewWidth);
    }

    public void setEnableLeftSildeEvent(boolean enableLeftSildeEvent) {
        this.enableLeftSildeEvent = enableLeftSildeEvent;
    }

    public void setEnableRightSildeEvent(boolean enableRightSildeEvent) {
        this.enableRightSildeEvent = enableRightSildeEvent;
    }

    /**
     * 设置OnSildingFinishListener, 在onSildingFinish()方法中finish Activity
     *
     * @param onSildingFinishListener
     */
    public void setOnSildingFinishListener(
            OnSildingFinishListener onSildingFinishListener) {
        this.onSildingFinishListener = onSildingFinishListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float downX = ev.getRawX();
        Log.i(TAG, "downX =" + downX + ",viewWidth=" + viewWidth);
        if (enableLeftSildeEvent && downX < size) {
            Log.e(TAG, "downX 在左侧范围内 ,拦截事件");
            isIntercept = true;
            isSwitchFromLeft = true;
            isSwitchFromRight = false;
            return false;
        } else if (enableRightSildeEvent && downX > (viewWidth - size)) {
//			Log.i(TAG, "downX 在右侧范围内 ,拦截事件");
            isIntercept = true;
            isSwitchFromRight = true;
            isSwitchFromLeft = false;
            return true;
        } else {
//			Log.i(TAG, "downX 不在范围内 ,不拦截事件");
            isIntercept = false;
            isSwitchFromLeft = false;
            isSwitchFromRight = false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("LongLogTag")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isIntercept) {//不拦截事件时 不处理
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = tempX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int deltaX = tempX - moveX;
                tempX = moveX;
                if (Math.abs(moveX - downX) > mTouchSlop && Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
                    isSilding = true;
                }

                Log.e(TAG, "scroll deltaX=" + deltaX);
                if (enableLeftSildeEvent) {//左侧滑动
                    if (moveX - downX >= 0 && isSilding) {
                        mParentView.scrollBy(deltaX, 0);
                    }
                }

                if (enableRightSildeEvent) {//右侧滑动
                    if (moveX - downX <= 0 && isSilding) {
                        mParentView.scrollBy(deltaX, 0);
                    }
                }

                Log.i(TAG + "/onTouchEvent", "mParentView.getScrollX()=" + mParentView.getScrollX());
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                //修改 viewWidth /3 由2变为3
                //mParentView.getScrollX() <= -viewWidth / 2  ==>指左侧滑动
                //mParentView.getScrollX() >= viewWidth / 2   ==>指右侧滑动
                if (mParentView.getScrollX() <= -viewWidth /3 || mParentView.getScrollX() >= viewWidth / 3) {
                    canSwitch = true;
                    if (isSwitchFromLeft) {
                        scrollToRight();
                    }

                    if (isSwitchFromRight) {
                        scrollToLeft();
                    }
                } else {
                    scrollOrigin();
                    canSwitch = false;
                }
                break;

        }
        return true;
    }
    /**
     * 滚动出界面至右侧
     * 修改：-delta + 450 由1更改至450
     */
    private void scrollToRight() {
        final int delta = (viewWidth + mParentView.getScrollX());
        Log.e(TAG, "scrollToRight:delta "+delta );
        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        mScroller.startScroll(mParentView.getScrollX(), 0, -delta + 450, 0, Math.abs(delta));
        postInvalidate();
    }


    /**
     * 滚动出界面至左侧
     */
    private void scrollToLeft() {
        final int delta = (viewWidth - mParentView.getScrollX());

        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        mScroller.startScroll(mParentView.getScrollX(), 0, delta - 1, 0, Math.abs(delta));//此处就不可用+1，也不卡直接用delta
        postInvalidate();
    }

    /**
     * 滚动到起始位置
     */
    private void scrollOrigin() {
        int delta = mParentView.getScrollX();
        mScroller.startScroll(mParentView.getScrollX(), 0, -delta, 0,
                Math.abs(delta));
        postInvalidate();
    }

    @Override
    public void computeScroll(){
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (mScroller.computeScrollOffset()) {
            mParentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

            if (mScroller.isFinished()) {
                if (onSildingFinishListener != null && canSwitch) {
                    Log.i(TAG, "mScroller finish");
                    if(isSwitchFromLeft){//回调，左侧切换事件
                        onSildingFinishListener.onSildingBack();
                    }

                    if(isSwitchFromRight){//右侧切换事件
                        onSildingFinishListener.onSildingForward();
                    }
                }
            }
        }
    }


    public interface OnSildingFinishListener {
        public void onSildingBack();

        public void onSildingForward();
    }
}

