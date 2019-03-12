package com.example.zhongdun.verifydemo.alarmclock;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a7invensun.verifydemo.R;

public class HintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        Vibrator vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }
}
