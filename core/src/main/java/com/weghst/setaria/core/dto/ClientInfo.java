package com.weghst.setaria.core.dto;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ClientInfo {

    private String host;
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
