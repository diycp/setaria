package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ClientInfo {

    /**
     * 主机地址.
     */
    private String host;
    /**
     * 最后拉取配置时间 (unix timestamp).
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
