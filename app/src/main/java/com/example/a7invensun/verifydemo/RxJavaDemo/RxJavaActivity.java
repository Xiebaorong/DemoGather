package com.example.a7invensun.verifydemo.RxJavaDemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.RxJavaDemo.model.StudentModel;
import com.example.a7invensun.verifydemo.greendao.model.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaActivity";
    private TextView tvShowLog;
    private StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        tvShowLog = findViewById(R.id.tv_show_Log);
        List<StudentModel.Courses> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentModel.Courses courses = new StudentModel.Courses("课程:" + i);
            list.add(courses);

        }
        studentModel = new StudentModel("张三", list);
    }

    public void rxOnClick(View view) {
        demo_4();
    }

    private void demo_4() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {


            }

        }).observeOn(Schedulers.io()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    /**
     * 验证线程
     */
    private void demo_3() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                Log.i(TAG, "1:" + Thread.currentThread().getName());
                observableEmitter.onNext(1);
                observableEmitter.onComplete();
            }
        });
        observable
//                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "2:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn( Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.i(TAG, "3:" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "4:" + Thread.currentThread().getName());
                    }
                });

    }

    @SuppressLint("CheckResult")
    private void demo_2() {
        Observable.just(studentModel.getCourses())
                .flatMap(new Function<List<StudentModel.Courses>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(List<StudentModel.Courses> list) throws Exception {

                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < studentModel.getCourses().size(); i++) {
                            String name = studentModel.getCourses().get(i).getName();
                            Log.e(TAG, "apply--2: " + name);
                            strings.add(name);
                        }
                        return Observable.fromIterable(strings);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        tvShowLog.append(s + "\n");
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void demo_1() {
        for (int i = 0; i < studentModel.getCourses().size(); i++) {
            String name = studentModel.getCourses().get(i).getName();
            Log.e(TAG, "apply--1: " + name);
        }
        Observable.create(new ObservableOnSubscribe<StudentModel>() {
            @Override
            public void subscribe(ObservableEmitter<StudentModel> emitter) throws Exception {
                emitter.onNext(studentModel);
            }

        }).flatMap(new Function<StudentModel, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(StudentModel studentModel) throws Exception {

                List<String> list = new ArrayList<>();
                for (int i = 0; i < studentModel.getCourses().size(); i++) {
                    String name = studentModel.getCourses().get(i).getName();
                    Log.e(TAG, "apply--2: " + name);
                    list.add(name);
                }
                return Observable.fromIterable(list);

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tvShowLog.append(s + "\n");
            }
        });

    }


}
