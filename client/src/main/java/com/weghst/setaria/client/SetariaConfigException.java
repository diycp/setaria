package com.weghst.setaria.client;

/**
 * {@code SetariaConfig} 异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaConfigException extends RuntimeException {

    /**
     * 通过错误详细信息与目标异常创建 {@code SetariaConfigException}.
     *
     * @param message 详细信息
     * @param cause   目标异常
     */
    public SetariaConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 通过目标异常创建 {@code SetariaConfigException}.
     *
     * @param cause 目标异常
     */
    public SetariaConfigException(Throwable cause) {
        super(cause);
    }
}
