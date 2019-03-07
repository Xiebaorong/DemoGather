package com.example.a7invensun.verifydemo.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.a7invensun.verifydemo.R;

public class AnimationActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = findViewById(R.id.image);


    }

    /**
     * 缩放动画
     *
     * @return
     */
    private AnimatorSet scaleAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator resetX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1);
        ObjectAnimator resetY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1);
        animatorSet.play(resetX).with(resetY);
        return animatorSet;
    }

    /**
     * 动画
     *
     * @return
     */
    private AnimatorSet alphaAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator resetX = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0.3f);
        animatorSet.play(resetX);
        animatorSet.setDuration(500);
        return animatorSet;
    }

    /**
     * 旋转
     *
     * @return
     */
    private AnimatorSet rotationAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator resetX = ObjectAnimator.ofFloat(imageView, "rotation", 180, 0, 0);
        animatorSet.play(resetX);
        animatorSet.setDuration(500);
        return animatorSet;
    }


    /**
     * 平移
     *
     * @return
     */
    private AnimatorSet translationAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator resetX = ObjectAnimator.ofFloat(imageView, "translationX", 200);
        animatorSet.play(resetX);
        animatorSet.setDuration(500);
        return animatorSet;
    }

    /**
     * 平移旋转
     *
     * @return
     */
    private AnimatorSet translationOrRotationAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rota = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360, 0);
        ObjectAnimator tran = ObjectAnimator.ofFloat(imageView, "translationX", 400, 0);
        animatorSet.play(rota).with(tran);
        animatorSet.setDuration(500);
        return animatorSet;
    }

    /**
     * 扩大旋转
     *
     * @return
     */
    private AnimatorSet translationOrScaleAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2f);
        ObjectAnimator rota = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360, 0);
        animatorSet.play(scaleX).with(scaleY).with(rota);
        animatorSet.setDuration(1000);
        return animatorSet;
    }

    public void scaleAnimationClick(View view) {
        scaleAnimation().start();
    }

    public void alphaAnimationClick(View view) {
        alphaAnimation().start();
    }

    public void rotationAnimationClick(View view) {
        rotationAnimation().start();
    }


    public void translationAnimationClick(View view) {
        translationAnimation().start();
    }

    public void translationorrotationAnimationClick(View view) {
        translationOrRotationAnimation().start();
    }

    public void translationorscaleAnimationClick(View view) {
        translationOrScaleAnimation().start();
    }
}
