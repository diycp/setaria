package com.weghst.setaria.console.web;

/**
 * Restful 结果返回.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Result {

    /**
     * 成功结果.
     */
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

    /**
     * 通过数据对象构建返回结果.
     *
     * @param data 数据对象
     */
    public Result(Object data) {
        this.data = data;
    }

    /**
     * 返回数据对象.
     *
     * @return 数据对象
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置数据对象.
     *
     * @param data 数据对象
     */
    public void setData(Object data) {
        this.data = data;
    }
}
