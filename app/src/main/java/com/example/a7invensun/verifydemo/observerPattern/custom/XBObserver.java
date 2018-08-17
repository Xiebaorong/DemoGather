package com.example.a7invensun.verifydemo.observerPattern.custom;

import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class XBObserver implements Observer {

    @Override
    public void action(Object... objects) {
        if (objects.length > 0 && objects[0] != null) {
            LogUtils.e(objects[0].toString() + "XBObserver ");
        }
    }
}
