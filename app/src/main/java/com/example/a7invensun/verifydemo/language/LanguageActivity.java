package com.example.a7invensun.verifydemo.language;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
//        Locale locale = Locale.getDefault();
//        String language = locale.getLanguage();
//        LogUtils.e(language);
//        changeLanguage(language);
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void changeLanguage(String language) {
//        Resources resources = getResources();
//        Configuration configuration = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        if (language.equals("zh")){
//            configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
//        }else if (language.equals("en")){
//            configuration.setLocale(Locale.ENGLISH);
//        } else if (language.equals("ja")){
//            configuration.setLocale(Locale.JAPAN);
//        }
//        resources.updateConfiguration(configuration,displayMetrics);
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void chineseClick(View view) {
        changeLanguage(1);
    }

    public void englishClick(View view) {
        changeLanguage(2);
    }

    public void japaneseClick(View view) {
        changeLanguage(3);
    }

    private void changeLanguage(int type) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        Locale locale = null;
        switch (type) {
            case 1:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case 2:
                locale = Locale.ENGLISH;
                break;
            case 3:
                locale = Locale.JAPAN;
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, displayMetrics);
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
