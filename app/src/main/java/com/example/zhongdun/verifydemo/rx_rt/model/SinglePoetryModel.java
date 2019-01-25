package com.example.zhongdun.verifydemo.rx_rt.model;

public class SinglePoetryModel {

    /**
     * code : 200
     * message : 成功!
     * result : {"author":"刘禹锡","origin":"浪淘沙·九曲黄河万里沙","category":"古诗文-山水-黄河","content":"九曲黄河万里沙，浪淘风簸自天涯。"}
     */

    private int code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * author : 刘禹锡
         * origin : 浪淘沙·九曲黄河万里沙
         * category : 古诗文-山水-黄河
         * content : 九曲黄河万里沙，浪淘风簸自天涯。
         */

        private String author;
        private String origin;
        private String category;
        private String content;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
