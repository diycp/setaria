package com.weghst.setaria.core.dto;

/**
 * 读取应用配置的客户端信息.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ClientInfo {

    /**
     * 客户端主机.
     */
    private String host;
    /**
     * 客户端最近拉取配置时间 (unix timestamp).
     */
    private long lastPullTime;

    public String getHost() {
        return host;
    }

    public ClientInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public long getLastPullTime() {
        return lastPullTime;
    }

    public ClientInfo setLastPullTime(long lastPullTime) {
        this.lastPullTime = lastPullTime;
        return this;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "host='" + host + '\'' +
                ", lastPullTime=" + lastPullTime +
                '}';
    }
}
