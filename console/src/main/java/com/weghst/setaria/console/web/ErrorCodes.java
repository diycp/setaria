package com.weghst.setaria.console.web;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public enum ErrorCodes {

    UNKNOWN(-1, "未知错误"),
    E_10001(10001, "用户不存在"),
    E_10002(10002, "密码错误"),
    //
    ;

    private final int code;
    private final String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCodes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
