package com.example.a7invensun.verifydemo.butterKnifeDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.butterKnifeDemo.util.ViewFindUtil;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeActivity extends AppCompatActivity {
    @BindView(R.id.text1)
    TextView textView1;
    @BindView(R.id.text2)
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
        ViewFindUtil viewFindUtil = new ViewFindUtil(this);
    }


    public void buttonknifeClick(View view) {
        textView1.setText(R.string.Japanese);
    }


}
