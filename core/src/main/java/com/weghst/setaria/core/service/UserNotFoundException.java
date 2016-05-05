package com.weghst.setaria.core.service;

/**
 * 未找到用户异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * 通过详细信息创建异常实例.
     *
     * @param message 详细信息
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
