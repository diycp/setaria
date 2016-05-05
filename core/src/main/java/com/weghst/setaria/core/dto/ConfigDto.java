package com.weghst.setaria.core.dto;

/**
 * 配置传输实体.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ConfigDto {

    /**
     * 配置键值.
     */
    private String key;
    /**
     * 配置属性值.
     */
    private String value;
    /**
     * 配置描述.
     */
    private String description;

    public String getKey() {
        return key;
    }

    public ConfigDto setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ConfigDto setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override public String toString() {
        return "ConfigDto{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
