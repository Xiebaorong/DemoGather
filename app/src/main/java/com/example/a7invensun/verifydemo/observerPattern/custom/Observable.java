package com.example.a7invensun.verifydemo.observerPattern.custom;

import java.util.Vector;

/**
 * Created by 7invensun on 2018/8/17.
 */

public abstract class Observable<T> {
    public Vector<T> obs = new Vector<>();

    /**
     * 注册观察者对象
     * @param t
     */
    public void registerObserver(T t) {
        isNull(t);
        obs.addElement(t);
    }

    /**
     * 注销观察者对象
     * @param t
     */
    public void unRegisterObserver(T t){
        isNull(t);
        obs.removeElement(t);
    }

    /**
     * 判断传入的观察者对象是否为空
     * @param t
     */
    private void isNull(T t) {
        if (t == null)
            throw new NullPointerException();
    }

    /**
     * 通知观察者做出行为改变
     *
     * @param objects
     */
    public abstract void notifyObservers(Object... objects);
}
