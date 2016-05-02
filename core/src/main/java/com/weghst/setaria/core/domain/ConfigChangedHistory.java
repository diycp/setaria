package com.weghst.setaria.core.domain;

import java.io.Serializable;

/**
 * 配置修改历史实体类型.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ConfigChangedHistory implements Serializable {

    private static final long serialVersionUID = -8877508277461318131L;

    /**
     * 增加操作.
     */
    public static final String ACTION_INSERT = "insert";
    /**
     * 更新操作.
     */
    public static final String ACTION_UPDATE = "update";
    /**
     * 删除操作.
     */
    public static final String ACTION_DELETE = "delete";

    /**
     * 主键.
     */
    private int id;
    private String appName;
    private Env appEnv;
    /**
     * 配置 ID.
     */
    private int configId;
    /**
     * 操作类型.
     */
    private String action;
    /**
     * 操作人员.
     */
    private String operator;
    /**
     * 原始配置信息.
     */
    private String original;
    /**
     * 改变后的配置信息.
     */
    private String changed;
    /**
     * 创建时间
     */
    private long createdTime;

    public int getId() {
        return id;
    }

    public ConfigChangedHistory setId(int id) {
        this.id = id;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public ConfigChangedHistory setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public Env getAppEnv() {
        return appEnv;
    }

    public ConfigChangedHistory setAppEnv(Env appEnv) {
        this.appEnv = appEnv;
        return this;
    }

    public int getConfigId() {
        return configId;
    }

    public ConfigChangedHistory setConfigId(int configId) {
        this.configId = configId;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ConfigChangedHistory setAction(String action) {
        this.action = action;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public ConfigChangedHistory setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getOriginal() {
        return original;
    }

    public ConfigChangedHistory setOriginal(String original) {
        this.original = original;
        return this;
    }

    public String getChanged() {
        return changed;
    }

    public ConfigChangedHistory setChanged(String changed) {
        this.changed = changed;
        return this;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public ConfigChangedHistory setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @Override
    public String toString() {
        return "ConfigChangedHistory{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", appEnv='" + appEnv + '\'' +
                ", configId=" + configId +
                ", action='" + action + '\'' +
                ", operator='" + operator + '\'' +
                ", original='" + original + '\'' +
                ", changed='" + changed + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
