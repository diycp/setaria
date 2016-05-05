package com.weghst.setaria.core.util;

/**
 * ZooKeeper 异常类型.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class ZooKeeperException extends RuntimeException {

    /**
     * 通过 ZooKeeper 异常创建实例.
     *
     * @param cause 目标异常
     */
    public ZooKeeperException(Throwable cause) {
        super(cause);
    }
}
