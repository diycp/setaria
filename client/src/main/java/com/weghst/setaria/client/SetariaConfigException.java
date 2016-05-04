package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaConfigException extends RuntimeException {

    /**
     * @param message
     * @param cause
     */
    public SetariaConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public SetariaConfigException(Throwable cause) {
        super(cause);
    }
}
