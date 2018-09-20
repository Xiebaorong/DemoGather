package com.example.a7invensun.verifydemo.mvpDemo.view;

import android.content.Context;
import android.text.SpannableString;

/**
 * Created by 7invensun on 2018/9/19.
 */

public interface ILoginView {
    Context getContext();

    void setUsername(String username);
    void setPassword(String password);

    void onLoginResult(Boolean result,int code);

    String getUsername();
    String getPassword();

    void send(SpannableString clickString);
}
