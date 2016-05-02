package com.weghst.setaria.core.domain;

import java.io.Serializable;

/**
 * 应用实体类型.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class App implements Serializable {

    private static final long serialVersionUID = 8712744054066753289L;

    /**
     * 主键.
     */
    private int id;
    /**
     * 应用名称.
     */
    private String name;
    /**
     * 应用环境.
     */
    private Env env;
    /**
     * 创建时间.
     */
    private long createdTime;
    /**
     * 最后修改时间.
     */
    private long lastUpdatedTime;
    /**
     * 应用描述.
     */
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", env=" + env +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", description='" + description + '\'' +
                '}';
    }
}
