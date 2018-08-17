package com.example.a7invensun.verifydemo.statusBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.statusBar.util.StatusBarUtils;

public class StatusBarShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_show);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorAccent);
    }

}
