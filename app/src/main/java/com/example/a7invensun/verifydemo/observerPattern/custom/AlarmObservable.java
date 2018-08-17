package com.example.a7invensun.verifydemo.observerPattern.custom;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class AlarmObservable extends Observable<Observer> {
    @Override
    public void notifyObservers(Object... objects) {
        for (Observer observer : obs) {
            observer.action(objects);
        }
    }
}
