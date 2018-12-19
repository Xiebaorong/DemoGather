package com.example.a7invensun.verifydemo.mvpDemo.model;

/**
 * Created by 7invensun on 2018/9/19.
 */

public class User {


    /**
     * status : 0
     * message : 成功
     * tall : {"name":"李四1","age":24,"tall":166}
     */

    private int status;
    private String message;
    private TallBean tall;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TallBean getTall() {
        return tall;
    }

    public void setTall(TallBean tall) {
        this.tall = tall;
    }

    public static class TallBean {
        /**
         * name : 李四1
         * age : 24
         * tall : 166
         */

        private String name;
        private int age;
        private int tall;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getTall() {
            return tall;
        }

        public void setTall(int tall) {
            this.tall = tall;
        }
    }
}
