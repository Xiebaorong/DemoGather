package com.example.a7invensun.verifydemo.observerPattern.custom;

/**
 * Created by 7invensun on 2018/8/17.
 */

public interface Observer {
    /**
     * 当被观察者变化时，及时反馈至观察者
     * @param objects
     */
    void action(Object... objects);
}
