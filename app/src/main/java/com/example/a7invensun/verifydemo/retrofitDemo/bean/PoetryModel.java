package com.example.a7invensun.verifydemo.retrofitDemo.bean;

public class PoetryModel {

    /**
     * code : 200
     * message : 成功!
     * result : {"title":"登胜果寺南楼雨中望严协律","content":"微雨侵晚阳，连山半藏碧。|林端陟香榭，云外迟来客。|孤村凝片烟，去水生远白。|但隹川原趣，不觉城池夕。|更喜眼中人，清光渐咫尺。","authors":"钱起"}
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
         * title : 登胜果寺南楼雨中望严协律
         * content : 微雨侵晚阳，连山半藏碧。|林端陟香榭，云外迟来客。|孤村凝片烟，去水生远白。|但隹川原趣，不觉城池夕。|更喜眼中人，清光渐咫尺。
         * authors : 钱起
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
