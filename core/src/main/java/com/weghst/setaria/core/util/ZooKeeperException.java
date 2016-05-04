package com.weghst.setaria.core.util;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class ZooKeeperException extends RuntimeException {

    /**
     * @param message
     */
    public ZooKeeperException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ZooKeeperException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public ZooKeeperException(Throwable cause) {
        super(cause);
    }
}
