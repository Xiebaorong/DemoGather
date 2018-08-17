package com.example.a7invensun.verifydemo.observerPattern.javaUtil;

import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class JavaUtilObserver implements Observer {
    public JavaUtilObserver(JavaUtilObservable ob) {
        super();
        ob.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        LogUtils.e(arg+"JavaUtilObserver");
    }
}
