package com.example.zhongdun.verifydemo.activemq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;

public class MQActivity extends AppCompatActivity {
    private TextView tvMq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mq);
        tvMq = findViewById(R.id.tv_mq);
        tvMq.setMovementMethod(ScrollingMovementMethod.getInstance());
        MqttService.setTextView(tvMq);
        MqttService.actionStart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MqttService.actionStop(this);
    }
}
