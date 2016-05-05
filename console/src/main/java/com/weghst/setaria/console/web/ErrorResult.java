package com.weghst.setaria.console.web;

/**
 * 错误结果返回.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ErrorResult {

    /**
     * 错误码.
     */
    private int errorCode;
    /**
     * 错误描述.
     */
    private String errorMessage;

    public ErrorResult() {
    }

    /**
     * 通过错误码构建 {@code Result} 实例.
     *
     * @param errorCodes {@link ErrorCodes}
     */
    public ErrorResult(ErrorCodes errorCodes) {
        this.errorCode = errorCodes.getCode();
        this.errorMessage = errorCodes.getMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorResult{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
