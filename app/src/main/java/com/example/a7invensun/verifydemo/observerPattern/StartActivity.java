package com.example.a7invensun.verifydemo.observerPattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.observerPattern.custom.AlarmObservable;
import com.example.a7invensun.verifydemo.observerPattern.custom.XBObserver;
import com.example.a7invensun.verifydemo.observerPattern.custom.XZObserver;
import com.example.a7invensun.verifydemo.observerPattern.javaUtil.JavaUtilObservable;
import com.example.a7invensun.verifydemo.observerPattern.javaUtil.JavaUtilObserver;
import com.example.a7invensun.verifydemo.observerPattern.javaUtil.JavaUtilObserver2;

public class StartActivity extends AppCompatActivity {
    AlarmObservable alarmObservable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        alarmObservable = new AlarmObservable();

    }

    private void main() {

    }

    /**
     * 按钮点击事件
     * @param view
     */
    public void alarmClick(View view) {
        alarmObservable = new AlarmObservable();
        XBObserver a = new XBObserver();
        XZObserver b = new XZObserver();
        alarmObservable.registerObserver(a);
        alarmObservable.registerObserver(b);
        alarmObservable.notifyObservers("产生变化----");
        alarmObservable.unRegisterObserver(a);
        alarmObservable.notifyObservers("产生变化----");
    }

    public void java_util_alarmClick(View view) {
        JavaUtilObservable observable = new JavaUtilObservable();
        JavaUtilObserver a = new JavaUtilObserver(observable);
        JavaUtilObserver2 b = new JavaUtilObserver2(observable);
        observable.changed("产生变化----");
        observable.deleteObserver(b);
        observable.changed("产生变化----");
    }

}
