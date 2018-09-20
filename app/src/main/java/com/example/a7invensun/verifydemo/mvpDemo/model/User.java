package com.example.a7invensun.verifydemo.mvpDemo.model;

/**
 * Created by 7invensun on 2018/9/19.
 */

public class User {
    int code;
    String name;
    String pass;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}
