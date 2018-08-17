package com.example.a7invensun.verifydemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.adapter.MyActivityAdapter;
import com.example.a7invensun.verifydemo.bottonClickDiffusion.DiffusionActivity;
import com.example.a7invensun.verifydemo.lockscreen.StartLockServiceActivity;
import com.example.a7invensun.verifydemo.statusBar.StatusBarShowActivity;
import com.example.a7invensun.verifydemo.util.ListData;

import java.util.ArrayList;
import java.util.List;

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
        list.add(new ListData(this, "状态栏样式", new Intent(this, StatusBarShowActivity.class)));
        list.add(new ListData(this, "锁屏", new Intent(this, StartLockServiceActivity.class)));
        list.add(new ListData(this, "按钮点击水波反馈", new Intent(this, DiffusionActivity.class)));
//        list.add(new ListData(this, "greenDao", new Intent(this, GreenDaoActivity.class)));
//        list.add(new ListData(this, "滑动", new Intent(this, SlideActivity.class)));
//        list.add(new ListData(this, "水平滑动", new Intent(this, HorizontalSlipActivity.class)));
//        list.add(new ListData(this, "Log管理", new Intent(this, LogActivity.class)));
//        list.add(new ListData(this, "观察者模式", new Intent(this, StartActivity.class)));
//        list.add(new ListData(this, "设置界面UI封装", new Intent(this, MultipleSetActivity.class)));
        Log.e(TAG, "initList: "+list.size());
        return list;
    }
}
