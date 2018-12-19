package com.example.a7invensun.verifydemo.retrofitDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.retrofitDemo.bean.PoetryModel;
import com.example.a7invensun.verifydemo.retrofitDemo.bean.Translation;
import com.example.a7invensun.verifydemo.retrofitDemo.interfacePack.GetRequest_Interface;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = "RetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

    }

    public void request() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.e(TAG, "onFailure: 连接失败");
            }
        });
    }

    public void retrofit_get_onClick(View view) {
//        request();
        ApiManager.getInstance().getApiService().findPoetryModel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

                .subscribe(new Observer<PoetryModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PoetryModel poetryModel) {
                        Log.e(TAG, "onNext: " + poetryModel.getResult().getContent());
                        showToast(poetryModel.getResult().getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: onComplete");
                    }
                });


    }


    public void retrofit_post_onClick(View view) {

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
