package com.example.a7invensun.verifydemo.multipleSet.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.a7invensun.verifydemo.multipleSet.MultipleSetActivity;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isMove() {
        return MultipleSetActivity.isMove; //记录的临时变量
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (true == isMove()) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }
}