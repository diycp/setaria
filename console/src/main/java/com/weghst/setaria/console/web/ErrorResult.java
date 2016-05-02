package com.weghst.setaria.console.web;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ErrorResult {

    private int errorCode;
    private String errorMessage;

    public ErrorResult() {
    }

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
