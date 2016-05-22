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
package com.weghst.setaria.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SetariaConfig 配置参数 Bean 对象定论.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaBean {

    @JsonProperty("setaria.config.resources")
    private List<Resource> resources = new ArrayList<>();
    @JsonProperty("setaria.config.zookeeper.connectString")
    private String zkConnectString;
    @JsonProperty("setaria.config.zookeeper.sessionTimeout")
    private int zkSessionTimeout;
    @JsonProperty("setaria.config.zookeeper.basePath")
    private String zkBasePath;
    @JsonProperty("setaria.config.zookeeper.app")
    private String zkApp;
    @JsonProperty("setaria.config.zookeeper.env")
    private String zkEnv;

    /**
     * 返回配置文件资源集合.
     *
     * @return 配置文件资源集合
     */
    public List<Resource> getResources() {
        return new ArrayList<>(resources);
    }

    /**
     * 设置配置文件资源集合.
     *
     * @param resources 配置文件资源集合
     */
    public void setResources(List<Resource> resources) {
        this.resources.clear();
        this.resources.addAll(resources);
    }

    /**
     * 添加配置文件资源.
     *
     * @param resource 配置文件
     * @return true/false
     */
    public boolean addResource(Resource resource) {
        return resources.add(resource);
    }

    /**
     * 根据配置文件路径添加配置资源.
     *
     * @param location 配置文件路径
     * @return true/false
     */
    public boolean addResource(String location) {
        Resource resource = new Resource();
        resource.setLocation(location);
        return addResource(resource);
    }

    /**
     * 根据配置文件路径添加配置资源.
     *
     * @param location       配置文件路径
     * @param ignoreNotFound 如果资源不存在是否忽略
     * @return true/false
     */
    public boolean addResource(String location, boolean ignoreNotFound) {
        Resource resource = new Resource();
        resource.setLocation(location);
        resource.setIgnoreNotFound(ignoreNotFound);
        return addResource(resource);
    }

    /**
     * 返回 ZooKeeper 连接字符串.
     *
     * @return ZooKeeper 连接字符串
     */
    public String getZkConnectString() {
        return zkConnectString;
    }

    /**
     * 设置 ZooKeeper 连接字符串.
     *
     * @param zkConnectString ZooKeeper 连接字符串
     */
    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    /**
     * 返回 ZooKeeper Session 超时时间.
     *
     * @return ZooKeeper Session 超时时间
     */
    public int getZkSessionTimeout() {
        return zkSessionTimeout;
    }

    /**
     * 设置 ZooKeeper Session 超时时间.
     *
     * @param zkSessionTimeout ZooKeeper Session 超时时间
     */
    public void setZkSessionTimeout(int zkSessionTimeout) {
        this.zkSessionTimeout = zkSessionTimeout;
    }

    /**
     * 返回 Setaria 在 ZooKeeper 中的根目录名称.
     *
     * @return 根目录名称
     */
    public String getZkBasePath() {
        return zkBasePath;
    }

    /**
     * 设置 Setaria 在 ZooKeeper 中的根目录名称.
     *
     * @param zkBasePath 根目录名称
     */
    public void setZkBasePath(String zkBasePath) {
        this.zkBasePath = zkBasePath;
    }

    /**
     * 返回分布式配置的应用名称.
     *
     * @return 应用名称
     */
    public String getZkApp() {
        return zkApp;
    }

    /**
     * 设置分布式配置的应用名称.
     *
     * @param zkApp 应用名称
     */
    public void setZkApp(String zkApp) {
        this.zkApp = zkApp;
    }

    /**
     * 返回分布式配置的应用环境.
     *
     * @return 应用环境
     */
    public String getZkEnv() {
        return zkEnv;
    }

    /**
     * 设置分布式配置的应用环境.
     *
     * @param zkEnv 应用环境
     */
    public void setZkEnv(String zkEnv) {
        this.zkEnv = zkEnv;
    }

    /**
     * Setaria 文件配置的资源类型.
     */
    public static class Resource {

        private String location;
        private boolean ignoreNotFound;

        /**
         * 返回资源路径表达式.
         *
         * @return 资源路径表达式
         */
        public String getLocation() {
            return location;
        }

        /**
         * 设置资源路径表达式.
         *
         * @param location 资源路径表达式
         */
        public void setLocation(String location) {
            this.location = location;
        }

        /**
         * 如果资源不存在是否忽略.
         *
         * @return true/false
         */
        public boolean isIgnoreNotFound() {
            return ignoreNotFound;
        }

        /**
         * 如果资源不存在是否忽略
         *
         * @param ignoreNotFound true/false
         */
        public void setIgnoreNotFound(boolean ignoreNotFound) {
            this.ignoreNotFound = ignoreNotFound;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "location='" + location + '\'' +
                    ", ignoreNotFound=" + ignoreNotFound +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SetariaBean{" +
                "resources=" + resources +
                ", zkConnectString='" + zkConnectString + '\'' +
                ", zkSessionTimeout=" + zkSessionTimeout +
                ", zkBasePath='" + zkBasePath + '\'' +
                ", zkApp='" + zkApp + '\'' +
                ", zkEnv='" + zkEnv + '\'' +
                '}';
    }
}
