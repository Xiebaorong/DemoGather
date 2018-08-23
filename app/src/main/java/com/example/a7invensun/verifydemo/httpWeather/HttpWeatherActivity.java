package com.example.a7invensun.verifydemo.httpWeather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.httpWeather.callBack.LoadCallBack;
import com.example.a7invensun.verifydemo.httpWeather.util.OkHttpManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class HttpWeatherActivity extends AppCompatActivity {
    private static final String TAG = "HttpWeatherActivity";
    @BindView(R.id.msg_text)
    TextView msgText;
    @BindView(R.id.getRe)
    Button getRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_weather);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.getRe)
    public void getRequestModel(View view) {
        OkHttpManager.getInstance().getRequest("http://www.weather.com.cn/data/sk/101190408.html");
    }

    public void getNewRequestModel(View view) {
        String url = "http://www.weather.com.cn/data/sk/101190408.html";
        Map<String, String> params = new HashMap<>();
        params.put("111","222");
        OkHttpManager.getInstance().newGetRequest(url, params, new LoadCallBack<String>(this) {
            @Override
            public void onSuccess(Call call, Response response, String result) {
                Log.e(TAG, "onSuccess: "+result );
            }

            @Override
            public void onError(Call call, int statusCode, Exception e) {

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }


        });
    }

    public void postRequestModel(View view) {
        String url = "http://www.weather.com.cn/data/sk/101190408.html";
        Map<String, String> params = new HashMap<>();
        OkHttpManager.getInstance().poseRequest(url,params);
    }
}
