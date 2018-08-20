package com.example.a7invensun.verifydemo.videopaly;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

public class VideoPalyActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback, UniversalMediaController.MediaPlayerControl {
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

        mMediaController.setTitle("动画片");
//        mMediaController.hideLoading();
        mMediaController.show();
       ;
        if (!mVideoView.isPlaying()) {
            LogUtils.e("判断是否为空"+ mVideoView.isPlaying());
        }
            mMediaController.hideComplete();
        mVideoView.setVideoViewCallback(this);

        //不添加会导致无视频播放时点击窗口转换，程序会崩溃。
        mMediaController.setMediaPlayer(this);
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        mVideoView.setVideoURI(uri);
        mVideoView.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height = mVideoView.getHeight();
        LogUtils.e(mVideoView.getHeight() + "");
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        LogUtils.e("窗口改变");
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoView.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.height;
            mVideoLayout.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        LogUtils.e("onPause");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        LogUtils.e("onStart");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }


    @Override
    public void start() {
        LogUtils.e("播放");
    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public void closePlayer() {

    }

    @Override
    public void setFullscreen(boolean fullscreen) {

    }

    @Override
    public void setFullscreen(boolean fullscreen, int screenOrientation) {

    }
}
