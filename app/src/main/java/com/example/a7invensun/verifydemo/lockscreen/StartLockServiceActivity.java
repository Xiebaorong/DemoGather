package com.example.a7invensun.verifydemo.lockscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a7invensun.verifydemo.R;

public class StartLockServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lock_service);
        startService(new Intent(this, LockScreenService.class));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, LockScreenService.class));
    }
}
