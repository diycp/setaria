package com.weghst.setaria.core.service;

/**
 * 密码不匹配异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PasswordNotMatchedException extends RuntimeException {

    /**
     * 通过详细信息创建异常实例.
     *
     * @param message 详细信息
     */
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
