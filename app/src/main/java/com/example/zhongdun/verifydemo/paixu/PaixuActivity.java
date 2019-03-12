package com.example.zhongdun.verifydemo.paixu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.MainActivity;
import com.example.a7invensun.verifydemo.R;

public class PaixuActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paixu);
        imageView = findViewById(R.id.iv_yidong_paixu);

    }

    public void paixuClick(View view) {
        paixu();
    }

    private static final String TAG = "PaixuActivity";

    private void paixu() {
//        for (int i = 0; i < 10; i++) {
//            int random2 = (int) (Math.random() * 6) + 18;
//            Log.e(TAG, "paixu: " + Math.random() + "-----" + random2);
//        }
//        Log.e(TAG, "paixu:-----------------------------------" + "\n");

//横向
        ObjectAnimator txanimator = ObjectAnimator.ofFloat(imageView, "translationX", 0, -670);
        //纵向
        ObjectAnimator tyanimator = ObjectAnimator.ofFloat(imageView, "translationY", 0, -500
        );
        //透明  0是透明，1是不透明
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
//        //根据X或Y轴旋转
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(
//                imageView,
//                "rotationY",
//                0,
//                360
//        );
        //缩放
        ObjectAnimator scaleYanimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 0f);

        //缩放
        ObjectAnimator scaleXanimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(tyanimator).with(txanimator).with(animator).with(scaleXanimator).with(scaleYanimator);
        animatorSet.setDuration(3000);

        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd: 完毕");
            }
        });
    }
}
