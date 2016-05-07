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
