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
     * 应用配置版本.
     */
    private long version;
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    @Override public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", env=" + env +
                ", version=" + version +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", description='" + description + '\'' +
                '}';
    }
}
