package com.example.a7invensun.verifydemo.videopaly;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

public class VideoPalyActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {
    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;
    private FrameLayout mVideoLayout;
//    private LinearLayout mBottomLayout;
    private boolean isFullscreen;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_paly);
        initView();
    }

    private void initView() {
        mVideoView = findViewById(R.id.videoView);
        mMediaController = findViewById(R.id.media_controller);
//        mBottomLayout = findViewById(R.id.bottom_layout);
        mVideoLayout = findViewById(R.id.video_layout);
        mVideoView.setMediaController(mMediaController);


        mVideoView.setVideoViewCallback(this);
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        mVideoView.setVideoURI(uri);
        mVideoView.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height = mVideoView.getHeight();
        LogUtils.e(mVideoView.getHeight()+"");
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoView.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.GONE);
        }else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.height;
            mVideoLayout.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}
