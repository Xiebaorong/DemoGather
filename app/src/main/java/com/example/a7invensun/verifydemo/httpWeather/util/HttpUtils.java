package com.example.a7invensun.verifydemo.httpWeather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 7invensun on 2018/8/24.
 */

public class HttpUtils {

    /**
     * 网络是否连接;
     * @param context
     * @return
     */
    public  static boolean isNetworkConnected(Context context){
        final ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }
}
