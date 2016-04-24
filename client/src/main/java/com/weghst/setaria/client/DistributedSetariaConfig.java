package com.weghst.setaria.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DistributedSetariaConfig extends AbstractSetariaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DistributedSetariaConfig.class);
    private static final int SESSION_TIMEOUT = 3 * 1000;
    private static final String DEFAULT_BASE_PATH = "/com/weghst/setaria";

    /**
     *
     */
    public static final String CONFIG_ZOOKEEPER_CONNECT_STRING = "setaria.config.zookeeper.connectString";
    /**
     *
     */
    public static final String CONFIG_BASE_PATH = "setaria.config.zookeeper.basePath";
    /**
     *
     */
    public static final String CONFIG_APP = "setaria.config.zookeeper.app";
    /**
     *
     */
    public static final String CONFIG_ENV = "setaria.config.zookeeper.env";

    private ZooKeeper zooKeeper;
    private ConfigProvider configProvider;
    private String path;

    /**
     * @param configParameters
     */
    public DistributedSetariaConfig(Map<String, String> configParameters) {
        super(configParameters);

        String connectString = getConfigParameter(CONFIG_ZOOKEEPER_CONNECT_STRING);
        String basePath = configParameters.get(CONFIG_BASE_PATH);
        if (basePath == null || basePath.isEmpty()) {
            basePath = DEFAULT_BASE_PATH;
        }
        String app = getConfigParameter(CONFIG_APP);
        String env = getConfigParameter(CONFIG_ENV);

        path = Paths.get(basePath, app, env).toString();

        LOG.debug("分布式配置参数 connectString: {}, path: {}", connectString, path);
        PathUtils.validatePath(path);

        try {
            zooKeeper = new ZooKeeper(connectString, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                }
            });

        } catch (IOException e) {
            throw new SetariaConfigException(e);
        }
    }

    @Override
    protected void doInit() {
        LOG.debug("初始化配置");

        refresh0(getUrl());

        LOG.debug("初始化配置成功");
    }

    @Override
    protected void doRefresh() {
        LOG.debug("刷新配置");

        refresh0(getUrl());

        LOG.debug("刷新配置成功");
    }

    @Override
    protected void doDestroy() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ConfigProvider getConfigProvider() {
        return configProvider;
    }

    private String getUrl() {
        Stat stat = new Stat();
        try {
            byte[] bytes = zooKeeper.getData(path, new ConfigPathWatcher(), stat);
            String url = new String(bytes);

            LOG.debug("配置服务器地址[{}], STAT: {}", url, stat);
            return url;
        } catch (Exception e) {
            LOG.error("获取配置 URL 错误", e);
            throw new SetariaConfigException(e);
        }
    }

    private void refresh0(String url) {
        Properties properties = new Properties();
        for (ConfigBean configBean : loadConfigs(url)) {
            properties.setProperty(configBean.getName(), configBean.getValue());

            LOG.debug("配置项 -> [{}: {}]", configBean.getName(), configBean.getValue());
        }

        //
        //
        //
        configProvider = new DefaultConfigProvider(properties);
        LOG.debug("设置 ConfigProvider 成功");
    }

    private ConfigBean[] loadConfigs(String url) {
        LOG.debug("读取[{}]配置", url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            OkHttpClient httpClient = new OkHttpClient();
            Response response = httpClient.newCall(request).execute();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body().byteStream(), ConfigBean[].class);
        } catch (IOException e) {
            throw new RuntimeException("加载[" + url + "]配置失败", e);
        }
    }

    private class ConfigPathWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            LOG.debug("节点[{}]事件", event);

            if (event.getType() == Event.EventType.NodeDataChanged) {
                refresh();
            }
        }
    }
}
