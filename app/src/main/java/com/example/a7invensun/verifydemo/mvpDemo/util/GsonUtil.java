package com.example.a7invensun.verifydemo.mvpDemo.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by 7invensun on 2018/9/20.
 */

public class GsonUtil {

    public static Object jsontoBean(String result, Type type){
        return new Gson().fromJson(result,type);
    }
}
