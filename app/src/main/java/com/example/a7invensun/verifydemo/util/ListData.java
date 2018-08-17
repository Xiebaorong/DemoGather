package com.example.a7invensun.verifydemo.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class ListData {
    private Context context;
    private String DadaName;
    private Intent intent;

    public ListData(Context context, String dadaName, Intent intent) {
        this.context = context;
        DadaName = dadaName;
        this.intent = intent;
    }

    public String getDadaName() {
        return DadaName;
    }

    public Context getContext() {
        return context;
    }

    public void startActivity(){
        getContext().startActivity(intent);
    }
}
