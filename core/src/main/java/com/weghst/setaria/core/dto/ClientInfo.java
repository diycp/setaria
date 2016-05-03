package com.weghst.setaria.core.dto;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ClientInfo {

    private String hostAddress;
    private String hostName;
    private long lastPullTime;

    public String getHostAddress() {
        return hostAddress;
    }

    public ClientInfo setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public ClientInfo setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public long getLastPullTime() {
        return lastPullTime;
    }

    public ClientInfo setLastPullTime(long lastPullTime) {
        this.lastPullTime = lastPullTime;
        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"hostAddress\":\"").append(hostAddress).append("\",")
                .append("\"hostName\":\"").append(hostName).append("\",")
                .append("\"lastPullTime\":").append(lastPullTime);
        return sb.append("}").toString();
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "hostAddress='" + hostAddress + '\'' +
                ", hostName='" + hostName + '\'' +
                ", lastPullTime=" + lastPullTime +
                '}';
    }
}
