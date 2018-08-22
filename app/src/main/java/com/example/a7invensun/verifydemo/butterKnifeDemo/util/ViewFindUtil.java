package com.example.a7invensun.verifydemo.butterKnifeDemo.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 7invensun on 2018/8/21.
 */

public class ViewFindUtil {
    @BindView(R.id.title_text)
    TextView text;
    public ViewFindUtil(Context context) {
        View view = View.inflate(context, R.layout.input_dialog_layout, null);
        ButterKnife.bind(this,view);
        String s = text.getText().toString();
        LogUtils.e(s);

    }
}
