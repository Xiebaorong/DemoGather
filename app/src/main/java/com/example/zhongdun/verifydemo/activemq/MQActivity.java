package com.example.zhongdun.verifydemo.activemq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a7invensun.verifydemo.R;

public class MQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mq);
        MqttService.actionStart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MqttService.actionStop(this);
    }
}
