package com.example.a7invensun.verifydemo.observerPattern.javaUtil;

import java.util.Observable;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class JavaUtilObservable extends Observable {

    public synchronized void changed(Object arg) {
        setChanged();
        notifyObservers(arg);  //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
    }
}
