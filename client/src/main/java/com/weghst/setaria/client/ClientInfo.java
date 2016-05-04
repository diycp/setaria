package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ClientInfo {

    /**
     * 主机地址.
     */
    private String hostAddress;
    /**
     * 最后拉取配置时间 (unix timestamp).
     */
    private long lastPullTime;

    public String getHostAddress() {
        return hostAddress;
    }

    public ClientInfo setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
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
                "hostAddress='" + hostAddress + '\'' +
                ", lastPullTime=" + lastPullTime +
                '}';
    }
}
