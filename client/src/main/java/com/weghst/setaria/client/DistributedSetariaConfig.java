package com.weghst.setaria.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import org.apache.zookeeper.*;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DistributedSetariaConfig extends AbstractSetariaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DistributedSetariaConfig.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    {
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private static final int SESSION_TIMEOUT = 60 * 1000;
    private static final String DEFAULT_BASE_PATH = "/setaria";
    private static final String URL_NODE_PATH = "/url";
    private static final String APPS_NODE_PATH = "/apps";
    private static final String CLIENT_NODE_PATH = "/client-";

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
    private String basePath;
    private String path;
    private String app;
    private String env;

    /**
     * @param configParameters
     */
    public DistributedSetariaConfig(Map<String, String> configParameters) {
        super(configParameters);

        String connectString = getConfigParameter(CONFIG_ZOOKEEPER_CONNECT_STRING);
        basePath = configParameters.get(CONFIG_BASE_PATH);
        if (basePath == null || basePath.isEmpty()) {
            basePath = DEFAULT_BASE_PATH;
        }
        app = getConfigParameter(CONFIG_APP);
        env = getConfigParameter(CONFIG_ENV);

        // FIXME 优化
        path = normalizePath(Paths.get(basePath, APPS_NODE_PATH, app + "-" + env).toString());

        LOG.debug("分布式配置参数 connectString: {}, path: {}", connectString, path);
        PathUtils.validatePath(path);

        try {
            zooKeeper = new ZooKeeper(connectString, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (path.equals(event.getPath()) && event.getType() == Event.EventType.NodeDataChanged) {
                        refresh();
                    }
                }
            });
        } catch (IOException e) {
            LOG.error("连接 ZooKeeper 服务器错误 ->> {}", connectString, e);
            throw new SetariaConfigException(e);
        }
    }

    @Override
    protected void doInit() {
        LOG.debug("初始化配置");

        refresh0();

        LOG.debug("初始化配置成功");
    }

    @Override
    protected void doRefresh() {
        LOG.debug("刷新配置");

        refresh0();

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
            byte[] bytes = zooKeeper.getData(normalizePath(basePath + URL_NODE_PATH), false, stat);
            String url = new String(bytes);

            LOG.debug("配置服务器地址[{}], STAT: {}", url, stat);
            return url;
        } catch (Exception e) {
            LOG.error("获取配置 URL 错误", e);
            throw new SetariaConfigException(e);
        }
    }

    private void refresh0() {
        Stat stat = new Stat();
        try {
            byte[] bytes = zooKeeper.getData(path, true, stat);
            loadConfigs();
        } catch (Exception e) {
            throw new SetariaConfigException(e);
        }
    }

    private void refreshZkClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        InetAddress host;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }

        clientInfo.setHost(host.toString());
        clientInfo.setLastPullTime(System.currentTimeMillis() / 1000);

        byte[] bytes;
        try {
            bytes = OBJECT_MAPPER.writeValueAsBytes(clientInfo);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        try {
            zooKeeper.create(normalizePath(path + CLIENT_NODE_PATH), bytes, ZooDefs.Ids.READ_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            LOG.error("向 ZooKeeper 提交客户端信息错误 ->> {}", clientInfo, e);
            throw new SetariaConfigException(e);
        }
    }

    private void loadConfigs() {
        String url = getUrl();
        LOG.debug("读取 [{}] 配置", url);

        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder().addPathSegment(app).addPathSegment(env).build();
        Request request = new Request.Builder().url(httpUrl).get().build();
        ConfigBean[] configBeans;
        try {
            OkHttpClient httpClient = new OkHttpClient();
            Response response = httpClient.newCall(request).execute();
            configBeans = OBJECT_MAPPER.readValue(response.body().byteStream(), ConfigBean[].class);
        } catch (IOException e) {
            throw new SetariaConfigException("加载 [" + url + "] 配置失败", e);
        }

        // -----------------------------------------------------
        Properties properties = new Properties();
        for (ConfigBean configBean : configBeans) {
            properties.setProperty(configBean.getKey(), configBean.getValue());
            LOG.debug("配置项 ->> [{}: {}]", configBean.getKey(), configBean.getValue());
        }

        //
        //
        //
        configProvider = new DefaultConfigProvider(properties);
        LOG.debug("设置 ConfigProvider 成功");

        refreshZkClientInfo();
    }

    private String normalizePath(String path) {
        String normalizedPath = path.replaceAll("//+", "/").replaceAll("\\\\", "/").replaceFirst("(.+)/$", "$1");
        PathUtils.validatePath(normalizedPath);
        return normalizedPath;
    }

}
