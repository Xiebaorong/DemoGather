package com.example.a7invensun.verifydemo.mvpDemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a7invensun.verifydemo.R;

import java.text.SimpleDateFormat;

/**
 * SharedPreferences操作类
 */

public class SharedPreferencesUtil {
    private static final String TAG = "SharedPreferencesUtil";
    private static SharedPreferencesUtil instance;
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }

    public void initSharedPreferences(Context context) {
        this.mContext = context;
        sp = context.getSharedPreferences(mContext.getString(R.string.app_table), Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    /**
     * isFirst == true 为首次登录
     *
     * @return
     */
    public boolean isFirstLogin() {
        boolean isFirst = sp.getBoolean("isF", true);
        if (isFirst) {
            SharedPreferences.Editor edit1 = sp.edit();
            edit1 = sp.edit();
            edit1.putBoolean("isF", false);
            edit1.commit();
        }
        return isFirst;
    }

    public void saveUserMsg(String username, String pass, boolean isSave) {
        edit.putString("username", username);
        edit.putString("pass", pass);
        edit.putBoolean("isSave", isSave);
        edit.commit();
    }

    public void saveOtherMessage(String key, String value) {
        edit = sp.edit();
        this.edit.putString(key, value);
        this.edit.commit();
    }

    public Object getOtherMessage(String key) {
        return sp.getString(key, "");
    }


}
