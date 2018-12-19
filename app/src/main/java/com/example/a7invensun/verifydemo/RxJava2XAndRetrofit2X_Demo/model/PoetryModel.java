package com.example.a7invensun.verifydemo.RxJava2XAndRetrofit2X_Demo.model;

import java.util.List;

public class PoetryModel {

    /**
     * code : 200
     * message : 成功!
     * result : [{"title":"日诗","content":"欲出未出光辣达，千山万山如火发。|须臾走向天上来，逐却残星赶却月。","authors":"宋太祖"},{"title":"句","content":"未离海底千山黑，才到天中万国明。","authors":"宋太祖"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 日诗
         * content : 欲出未出光辣达，千山万山如火发。|须臾走向天上来，逐却残星赶却月。
         * authors : 宋太祖
         */

        private String title;
        private String content;
        private String authors;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthors() {
            return authors;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }
    }
}
