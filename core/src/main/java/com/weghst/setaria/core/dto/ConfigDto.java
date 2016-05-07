/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
