package com.weghst.setaria.core.service;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class AppNotFoundException extends RuntimeException {

    /**
     * @param message
     */
    public AppNotFoundException(String message) {
        super(message);
    }
}
