package com.weghst.setaria.core.service;

/**
 * 未找到应用异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class AppNotFoundException extends RuntimeException {

    /**
     * 通过详细信息构建异常.
     *
     * @param message 详细信息
     */
    public AppNotFoundException(String message) {
        super(message);
    }
}
