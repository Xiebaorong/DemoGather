package com.example.a7invensun.verifydemo.lockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class LockScreenService extends Service {
    private static final String TAG = "LockScreenService";
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction()==Intent.ACTION_SCREEN_OFF){
                Log.w(TAG, "onReceive: 收到锁屏广播" );
                Intent lockscreen = new Intent(LockScreenService.this, LockScreenActivity.class);
                lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(lockscreen);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(receiver, filter);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

}

