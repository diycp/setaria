package com.weghst.setaria.core.service;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * @param message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
