package com.example.a7invensun.verifydemo.horizontalSlip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.horizontalSlip.callback.OnUnlockListener;
import com.example.a7invensun.verifydemo.horizontalSlip.util.MySockView;

public class HorizontalSlipActivity extends AppCompatActivity implements OnUnlockListener {
    private static final String TAG = "HorizontalSlipActivity";
    private MySockView mySockView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_slip);
//        imageView = findViewById(R.id.imageView);
        mySockView =  findViewById(R.id.mySockView);
        mySockView.setUnOnClick(this);
    }

    @Override
    public void setUnlock(boolean unlock) {
        if(unlock){
            Log.e(TAG, "setUnlock: -----------" );
//            mySockView.setVisibility(View.GONE);
//            imageView.setVisibility(View.VISIBLE);
        }
    }
}
