package com.example.a7invensun.verifydemo.judgeNetworkConnected;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.judgeNetworkConnected.util.NetworkStateUtil;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

public class NetWorkConnectedActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_connected_activity);
        mContext = this;
    }

    public void newButton(View view) {
        boolean networkConnected = isNetworkConnected(mContext);
        LogUtils.e(networkConnected);
    }

    public boolean isNetworkConnected(Context context) {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo()!=null){
            return manager.getActiveNetworkInfo().isConnected();
        }
        return false;
    }

    public void netButton(View view) {
        netWorkState(mContext);
        NetworkStateUtil.isNetworkConnected(this);
    }

    private void netWorkState(Context mContext) {
        LogUtils.e("netWorkState");
        ConnectivityManager manager=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network  = manager.getActiveNetworkInfo();
        int type = ConnectivityManager.TYPE_DUMMY;
        if (network !=null){
            type = network.getType();
        }
        if (type == ConnectivityManager.TYPE_MOBILE) {
            LogUtils.v("mobile data is connected: "+ network.isConnected());
        } else if (type == ConnectivityManager.TYPE_WIFI){
            LogUtils.v("wifi is connected: "+ network.isConnected());
        }
        LogUtils.e(type);
    }
}
