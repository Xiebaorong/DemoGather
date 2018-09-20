package com.example.a7invensun.verifydemo.mvpDemo.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.mvpDemo.callBack.LoadCallBack;
import com.example.a7invensun.verifydemo.mvpDemo.presenter.ILoginPresenter;
import com.example.a7invensun.verifydemo.mvpDemo.presenter.LoginPresenterCompl;
import com.example.a7invensun.verifydemo.mvpDemo.util.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity implements ILoginView, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.iv_app_logo)
    ImageView ivAppLogo;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.et_name_login)
    EditText etNameLogin;
    @BindView(R.id.et_pass_login)
    EditText etPassLogin;
    @BindView(R.id.cb_rememberPass_login)
    CheckBox cbRememberPassLogin;
    @BindView(R.id.tv_registerAforget_login)
    TextView tvRegisterAforgetLogin;
    private LoginPresenterCompl loginPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void OnActCreate(Bundle savedInstanceState) {
        loginPresenter = new LoginPresenterCompl(this);
    }

    @Override
    protected void initView() {
        ivAppLogo.setBackgroundResource(R.mipmap.suo);
        tvAppName.setText(mContext.getText(R.string.app_name));

        cbRememberPassLogin.setOnCheckedChangeListener(this);

        String username = loginPresenter.getUsername();
        String password = loginPresenter.getPassword();
        if (!("").equals(username)|| !("").equals(password)) {
            etNameLogin.setText(username);
            etPassLogin.setText(password);
            cbRememberPassLogin.setChecked(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerOrForgetLogin();
    }

    private void registerOrForgetLogin() {

        SpannableString clickString = new SpannableString("我要注册");
        
        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG, "registerOrForgetLogin: 我要注册" );
                 startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);//设置颜色
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegisterAforgetLogin.append(clickString);
        tvRegisterAforgetLogin.append(new SpannableString(" / "));
        SpannableString clickString2 = new SpannableString("忘记密码");
        clickString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG, "registerOrForgetLogin: 忘记密码" );
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE); //设置颜色
            }
        }, 0, clickString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegisterAforgetLogin.append(clickString2);
        tvRegisterAforgetLogin.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }

    public void loginAppClick(View view) {
        if (etNameLogin.getText().toString().trim().equals("")) {
            sendToast("请输入用户名");
        } else if (etPassLogin.getText().toString().trim().equals("")) {
            sendToast("请输入密码");
        } else {
            loginPresenter.loginApp();
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        if (result) {
            sendToast("登录成功");
        } else {
            sendToast("失败");
        }
    }

    @Override
    public String getUsername() {
        return etNameLogin.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassLogin.getText().toString().trim();
    }

    @Override
    public void send(SpannableString clickString) {
        tvRegisterAforgetLogin.append(clickString);
        tvRegisterAforgetLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        loginPresenter.isSaveMag(isChecked);
    }



}
