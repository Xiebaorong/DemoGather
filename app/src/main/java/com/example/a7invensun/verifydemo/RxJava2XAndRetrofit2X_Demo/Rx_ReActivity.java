package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.apiservice.ApiService;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.callback.ObserverOnNextListener;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http.ApiMethods;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http.MyObserver;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http.ProgressObserver;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.http.URLUtils;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.MovieModel;
import com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model.PoetryModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rx_ReActivity extends AppCompatActivity {
    private static final String TAG = "Rx_ReActivity";
    private TextView tvShowMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx__re);
        tvShowMag = findViewById(R.id.tv_show_mag);
    }

    public void rx_reOnClick(View view) {
        showMag_5();
    }




    /**
     * 最基本的RxJava+Retrofit
     */
    private void showMag_1() {


        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLUtils.BASEURL)
                .client(build)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTopMovice(0, 2)
                .subscribeOn(Schedulers.io())//只有一次效果
                .observeOn(AndroidSchedulers.mainThread())//可多次
                .subscribe(new Observer<PoetryModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PoetryModel poetryModel) {
                        String result = poetryModel.getMessage() + "\n";
                        for (int i = 0; i < poetryModel.getResult().size(); i++) {
                            result += poetryModel.getResult().get(i).getTitle() + "\n"
                                    + poetryModel.getResult().get(i).getContent() + "\n"
                                    + poetryModel.getResult().get(i).getAuthors() + "\n";
                        }
                        tvShowMag.setText(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 基本的封装RxJava+Retrofit
     */
    private void showMag_2() {
        Observer<PoetryModel> observer = new Observer<PoetryModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PoetryModel poetryModel) {
                String result = poetryModel.getMessage() + "\n";
                for (int i = 0; i < poetryModel.getResult().size(); i++) {
                    result += poetryModel.getResult().get(i).getTitle() + "\n"
                            + poetryModel.getResult().get(i).getContent() + "\n"
                            + poetryModel.getResult().get(i).getAuthors() + "\n";
                }
                tvShowMag.setText(result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        ApiMethods.getPoetry(observer, 0, 3);
    }

    /**
     * 进一步封装RxJava+Retrofit
     */
    private void showMag_3() {
        ObserverOnNextListener<PoetryModel> listener = new ObserverOnNextListener<PoetryModel>() {
            @Override
            public void onNext(PoetryModel poetryModel) {
                String result = poetryModel.getMessage() + "\n";
                for (int i = 0; i < poetryModel.getResult().size(); i++) {
                    result += poetryModel.getResult().get(i).getTitle() + "\n"
                            + poetryModel.getResult().get(i).getContent() + "\n"
                            + poetryModel.getResult().get(i).getAuthors() + "\n";
                }
                tvShowMag.setText(result);
            }

            @Override
            public void onError(Throwable e) {

            }

        };
        ApiMethods.getPoetry(new MyObserver<PoetryModel>(this, listener), 0, 4);
    }

    private void showMag_4() {
        ObserverOnNextListener<PoetryModel> listener = new ObserverOnNextListener<PoetryModel>() {
            @Override
            public void onNext(PoetryModel poetryModel) {
                String result = poetryModel.getMessage() + "\n";
                for (int i = 0; i < poetryModel.getResult().size(); i++) {
                    result += poetryModel.getResult().get(i).getTitle() + "\n"
                            + poetryModel.getResult().get(i).getContent() + "\n"
                            + poetryModel.getResult().get(i).getAuthors() + "\n";
                }
                tvShowMag.setText(result);
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        ApiMethods.getPoetry(new ProgressObserver<PoetryModel>(this, listener), 0, 1);
    }

    private void showMag_5() {
        ObserverOnNextListener<PoetryModel> listener = new ObserverOnNextListener<PoetryModel>() {
            @Override
            public void onNext(PoetryModel poetryModel) {
                String result = poetryModel.getMessage() + "\n";
                for (int i = 0; i < poetryModel.getResult().size(); i++) {
                    result += poetryModel.getResult().get(i).getTitle() + "\n"
                            + poetryModel.getResult().get(i).getContent() + "\n"
                            + poetryModel.getResult().get(i).getAuthors() + "\n";
                }
                tvShowMag.setText(result);
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        ApiMethods.getStrategyPoetry(new ProgressObserver<PoetryModel>(this, listener), 0, 2);
        
    }

}
