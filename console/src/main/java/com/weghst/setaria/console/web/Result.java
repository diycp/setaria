package com.weghst.setaria.console.web;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Result {

    public static final Result SUCCESS = new Result() {

        @Override
        public Object getData() {
            return "success";
        }

        @Override
        public void setData(Object data) {
            throw new IllegalStateException("不允许修改 [SUCCESS] 属性值");
        }
    };

    private Object data;

    public Result() {

    }

    public Result(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
