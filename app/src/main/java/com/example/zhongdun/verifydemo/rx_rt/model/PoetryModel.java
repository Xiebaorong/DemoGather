package com.example.zhongdun.verifydemo.rx_rt.model;

public class PoetryModel {

    /**
     * code : 200
     * message : 成功!
     * result : {"title":"赠嗣持上人","content":"持也眈眈老比邱，吾如松涧瀑春流。|经年欲见浑无便，连日相逢若旧游。|柏子轩窗翻梵夹，荷香亭上款茶瓯。|真成此外无余事，莫说机关向上头。","authors":"张镃"}
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
         * title : 赠嗣持上人
         * content : 持也眈眈老比邱，吾如松涧瀑春流。|经年欲见浑无便，连日相逢若旧游。|柏子轩窗翻梵夹，荷香亭上款茶瓯。|真成此外无余事，莫说机关向上头。
         * authors : 张镃
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
