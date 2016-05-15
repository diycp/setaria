package com.weghst.setaria.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
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
     * @return
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * @param resources
     * @return
     */
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    /**
     * @param resource
     * @return
     */
    public boolean addResource(Resource resource) {
        return resources.add(resource);
    }

    /**
     * @param location
     * @return
     */
    public boolean addResource(String location) {
        return addResource(new Resource(location));
    }

    /**
     * @param location
     * @param ignoreNotFound
     * @return
     */
    public boolean addResource(String location, boolean ignoreNotFound) {
        return addResource(location, ignoreNotFound);
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    public int getZkSessionTimeout() {
        return zkSessionTimeout;
    }

    public void setZkSessionTimeout(int zkSessionTimeout) {
        this.zkSessionTimeout = zkSessionTimeout;
    }

    public String getZkBasePath() {
        return zkBasePath;
    }

    public void setZkBasePath(String zkBasePath) {
        this.zkBasePath = zkBasePath;
    }

    public String getZkApp() {
        return zkApp;
    }

    public void setZkApp(String zkApp) {
        this.zkApp = zkApp;
    }

    public String getZkEnv() {
        return zkEnv;
    }

    public void setZkEnv(String zkEnv) {
        this.zkEnv = zkEnv;
    }

    public static class Resource {

        private String location;
        private boolean ignoreNotFound;

        public Resource() {
        }

        public Resource(String location) {
            this.location = location;
        }

        public Resource(String location, boolean ignoreNotFound) {
            this.location = location;
            this.ignoreNotFound = ignoreNotFound;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public boolean isIgnoreNotFound() {
            return ignoreNotFound;
        }

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
