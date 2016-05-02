package com.weghst.setaria.core.service;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PasswordNotMatchedException extends RuntimeException {

    /**
     *
     * @param message
     */
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
