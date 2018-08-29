package com.example.a7invensun.verifydemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.adapter.MyActivityAdapter;
import com.example.a7invensun.verifydemo.animation.AnimationActivity;
import com.example.a7invensun.verifydemo.bottonClickDiffusion.DiffusionActivity;
import com.example.a7invensun.verifydemo.butterKnifeDemo.ButterKnifeActivity;
import com.example.a7invensun.verifydemo.dialog.DialogActivity;
import com.example.a7invensun.verifydemo.greendao.GreenDaoActivity;
import com.example.a7invensun.verifydemo.horizontalSlip.HorizontalSlipActivity;
import com.example.a7invensun.verifydemo.httpWeather.HttpWeatherActivity;
import com.example.a7invensun.verifydemo.judgeNetworkConnected.NetWorkConnectedActivity;
import com.example.a7invensun.verifydemo.language.LanguageActivity;
import com.example.a7invensun.verifydemo.lockscreen.StartLockServiceActivity;
import com.example.a7invensun.verifydemo.logdemo.LogActivity;
import com.example.a7invensun.verifydemo.multipleSet.MultipleSetActivity;
import com.example.a7invensun.verifydemo.observerPattern.StartActivity;
import com.example.a7invensun.verifydemo.retrofitDemo.RetrofitActivity;
import com.example.a7invensun.verifydemo.slide.SlideActivity;
import com.example.a7invensun.verifydemo.statusBar.StatusBarShowActivity;
import com.example.a7invensun.verifydemo.util.ListData;
import com.example.a7invensun.verifydemo.videopaly.VideoPalyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.show_demo);
        final List<ListData> listData = initList();
        MyActivityAdapter adapter  = new MyActivityAdapter(this,listData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listData.get(position).startActivity();
                TextView textview = view.findViewById(android.R.id.text1);
                textview.setText(listData.get(position).getDadaName() + "");
            }
        });
    }

    private List<ListData> initList() {
        List<ListData> list = new ArrayList<>();
        list.add(new ListData(this, "设置界面UI封装", new Intent(this, MultipleSetActivity.class)));
        list.add(new ListData(this, "观察者模式", new Intent(this, StartActivity.class)));
        list.add(new ListData(this, "锁屏", new Intent(this, StartLockServiceActivity.class)));
        list.add(new ListData(this, "Log管理(无样式，log输出)", new Intent(this, LogActivity.class)));
        list.add(new ListData(this, "水平滑动", new Intent(this, HorizontalSlipActivity.class)));
        list.add(new ListData(this, "GreenDao框架Demo", new Intent(this, GreenDaoActivity.class)));
        list.add(new ListData(this, "自定义弹框", new Intent(this, DialogActivity.class)));
        list.add(new ListData(this, "状态栏样式、TextView滑动显示内容", new Intent(this, StatusBarShowActivity.class)));
        list.add(new ListData(this, "按钮点击水波反馈", new Intent(this, DiffusionActivity.class)));
        list.add(new ListData(this, "滑动", new Intent(this, SlideActivity.class)));
        list.add(new ListData(this, "网络状态", new Intent(this, NetWorkConnectedActivity.class)));
        list.add(new ListData(this, "视频播放", new Intent(this, VideoPalyActivity.class)));
        list.add(new ListData(this, "动画", new Intent(this, AnimationActivity.class)));
        list.add(new ListData(this, "中英文切换", new Intent(this, LanguageActivity.class)));
        list.add(new ListData(this, "ButterKnife，Activity中有find 与 view.find 两种情况", new Intent(this, ButterKnifeActivity.class)));
        list.add(new ListData(this, "OkHttp", new Intent(this, HttpWeatherActivity.class)));
        list.add(new ListData(this, "Retrofit", new Intent(this, RetrofitActivity.class)));

        Log.e(TAG, "initList: "+list.size());
        return list;
    }
}
