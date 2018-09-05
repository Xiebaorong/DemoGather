package com.example.a7invensun.verifydemo.cameraDemo.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.camera2.params.Face;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by 7invensun on 2018/9/4.
 */

public class FaceView extends View {
    private static final String TAG = "FaceView";
    private Rect rect;
    private Size size;
    private Face[] face;

    public FaceView(Context context) {
        super(context);
    }

    public FaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFaceView(Rect bounds, Size mCaptureSize, Face[] faces) {
        rect = bounds;
        size = mCaptureSize;
        face = faces;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e(TAG, "process:--->top: " + rect.top + "--- bottom: " + rect.bottom + "---left: " + rect.left + "---right: " + rect.right);
        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.BLUE);// 设置灰色
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.STROKE);//使绘制的矩形中空

        float scaleWidth = canvas.getHeight() * 1.0f / size.getWidth();
        float scaleHeight = canvas.getWidth() * 1.0f / size.getHeight();
        Log.e(TAG, "onDraw:scaleWidth " + scaleWidth + "---scaleHeight:" + scaleHeight + "---size.getHeight():" + size.getHeight()+"---size.getWidth()"+size.getWidth()+"---canvas.getHeight():"+canvas.getHeight()+"---canvas.getWidth() "+canvas.getWidth() );

//      坐标缩放
        int l = (int) (rect.left * scaleWidth);
        int t = (int) (rect.top * scaleHeight);
        int r = (int) (rect.right * scaleWidth);
        int b = (int) (rect.bottom * scaleHeight);
        Log.e(TAG, "[T" + 0 + "]:[left:" + l + ",top:" + t + ",right:" + r + ",bottom:" + b + "]");
//        canvas.drawRect(100 , 100, 100, 100, p);// 长方形
//        if (face.length>0){
//
//            Point leftEyePosition = face[0].getLeftEyePosition();
//            Point rightEyePosition = face[0].getRightEyePosition();
//            Point mouthPosition = face[0].getMouthPosition();
//            Log.e(TAG, "onDraw: "+leftEyePosition.x  );
//            int left = leftEyePosition.x - 100;
//            int top = leftEyePosition.y + 100;
//            int right = rightEyePosition.x + 100;
//            int bottom = mouthPosition.y - 100;
//            canvas.drawRect(left, top, right, bottom, p);// 长方形
//        }
        canvas.drawRect(canvas.getWidth()-b,canvas.getHeight()-r,canvas.getWidth()-t,canvas.getHeight()-l,p);// 长方形
//        canvas.drawRect(0, 550, 550, 0, p);// 长方形
    }
}
