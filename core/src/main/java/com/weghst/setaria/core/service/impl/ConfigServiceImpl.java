package com.weghst.setaria.core.service.impl;

import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.service.ConfigService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class ConfigServiceImpl implements ConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Override
    public void save(Config config) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("", new RetryPolicy() {
            @Override
            public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                LOG.error("ZooKeeper 连接断开");
                return false;
            }
        });


    }

    @Override
    public void update(Config config) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Config> findByApp(int appId) {
        return null;
    }

    @Override
    public List<Config> findByAppNameAndAppEnv(String appName, String appEnv) {
        return null;
    }
}
