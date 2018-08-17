package com.example.a7invensun.verifydemo.horizontalSlip.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.horizontalSlip.callback.OnUnlockListener;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class MySockView extends View {
    private static final String TAG = "MySockView";
    private int screenHweight;
    private int screenWidth;
    private Bitmap jiesuo_bg;
    private Bitmap jiesuo_button;
    private int jiesuo_bgWidth;
    private int jiesuo_bgHeight;
    private int jiesuo_buttonWidth;
    private int right;
    private int top;
    private int left;
    private int currentX;
    private int currentY;
    private Paint paint;
    private boolean onBlock;
    private OnUnlockListener onUnlockListener;

    //解锁之后要监听的方法
    public void setUnOnClick(OnUnlockListener onUnlockListener) {
        this.onUnlockListener = onUnlockListener;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //如果没有滑动到解锁的指定位置，就返回到起始位置
            //判断如果滑动的小球在指定范围内
            if (currentX > left) {
                //慢慢回到起始位置
                currentX = currentX - 10;
                //重置界面
                invalidate();
                sendEmptyMessageDelayed(0, 2);
            }
        }
    };

    public MySockView(Context context) {
        super(context);
        init();
    }

    public MySockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Bitmap jiesuo_bg1 = BitmapFactory.decodeResource(getResources(), R.mipmap.beijing);
        Bitmap jiesuo_button1 = BitmapFactory.decodeResource(getResources(), R.mipmap.suo);
        DisplayMetrics metric = new DisplayMetrics();

        int windowsWight = metric.widthPixels;
        int windowsHeight = metric.heightPixels;
        Log.e(TAG, "init: "+getScreenWidth(getContext())  );
        jiesuo_bg = setImgSize(jiesuo_bg1, getScreenWidth(getContext()) / 2, 150);
        jiesuo_button = setImgSize(jiesuo_button1, 130, 130);
        jiesuo_bgWidth = jiesuo_bg.getWidth();
        jiesuo_bgHeight = jiesuo_bg.getHeight();
        jiesuo_buttonWidth = jiesuo_button.getWidth();
    }

    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public Bitmap setImgSize(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到屏幕的宽高
        screenHweight = getMeasuredHeight();
        screenWidth = getMeasuredWidth();
        //计算出图片距离上、左、右的长度
//        left = screenWidth / 2 - jiesuo_bgWidth / 2;
//        top = screenHweight / 2 - jiesuo_bgHeight / 2;

        //计算出滑动小球距离右边的距离
//        right = screenWidth / 2 + jiesuo_bgWidth / 2 - jiesuo_buttonWidth;
        right = jiesuo_bgWidth - jiesuo_buttonWidth;
        currentX = left;
        currentY = top;
        //创建一个画笔
        paint = new Paint();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制解锁图片
        canvas.drawBitmap(jiesuo_bg, 0, 0, paint);
//        canvas.drawBitmap(jiesuo_bg,dst,paint);

        //边界点判断
        if (currentX < left) {
            currentX = left;
        } else if (currentX > right) {
            currentX = right;
        }
        canvas.drawBitmap(jiesuo_button, currentX, currentY, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断一下，当前手指是否按在了小滑块上
                float downX = event.getX();
                float downY = event.getY();
                onBlock = isOnBlock(downX, downY);
                if (onBlock) {
                    //移除所有的消息
                    handler.removeCallbacksAndMessages(null);
                    Toast.makeText(getContext(), "按到了", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (onBlock) {
                    //获取move的x和y坐标
                    float moveX = event.getX();
                    float moveY = event.getY();
                    currentX = (int) (moveX - jiesuo_buttonWidth / 2);
                    //重新绘制
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                onBlock = false;
                //判断抬起时小球的位置
                if (currentX >= right - 3) {
                    //解锁了
                    if (onUnlockListener != null) {
                        onUnlockListener.setUnlock(true);
                    }
                    Toast.makeText(getContext(), "解锁了", Toast.LENGTH_SHORT).show();
                } else {
                    //返回原位置
//                    currentX=left;
                    handler.sendEmptyMessageDelayed(0, 2);
                }
                invalidate();
                break;

        }
        return true;
    }

    //判断是否按在圆球上
    public boolean isOnBlock(float downX, float downY) {
        //先计算圆心位置
        int rx = currentX + jiesuo_buttonWidth / 2;
        int ry = currentY + jiesuo_buttonWidth / 2;
        //计算手指按下点和圆心的距离
        double sqrt = Math.sqrt((downX - rx) * (downX - rx) + (downY - ry) * (downY - ry));
        if (sqrt <= jiesuo_buttonWidth / 2) {
            return true;
        }
        return false;
    }


}

