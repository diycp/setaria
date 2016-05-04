package com.weghst.setaria.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weghst.setaria.core.ObjectMapperUtils;
import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.domain.ConfigChangedHistory;
import com.weghst.setaria.core.domain.Env;
import com.weghst.setaria.core.dto.ConfigDto;
import com.weghst.setaria.core.repository.AppRepository;
import com.weghst.setaria.core.repository.ConfigChangedHistoryRepository;
import com.weghst.setaria.core.repository.ConfigRepository;
import com.weghst.setaria.core.service.AppNotFoundException;
import com.weghst.setaria.core.service.ConfigChangedEvent;
import com.weghst.setaria.core.service.ConfigService;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ConfigChangedHistoryRepository configChangedHistoryRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    // -------------------------------------------------
    @Value("${setaria.zookeeper.servers}")
    private String zookeeperServers;
    @Value("${setaria.zookeeper.basePath}")
    private String zookeeperBasePath;
    @Value("${setaria.pull.config.url}")
    private String pullConfigUrl;

    @Override
    public void save(Config config, String operator) {
        App app = appRepository.findById(config.getAppId());

        config.setCreatedTime(System.currentTimeMillis());
        configRepository.save(config);

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(config,
                ConfigChangedHistory.ACTION_INSERT, operator)
                .setConfigId(config.getId())
                .setChanged(configToJson(config));
        configChangedHistoryRepository.save(configChangedHistory);

        // 配置更新通知
        applicationEventPublisher.publishEvent(new ConfigChangedEvent(app));
    }

    @Override
    public void update(Config config, String operator) {
        Config dbConfig = configRepository.findById(config.getId());
        App app = appRepository.findById(dbConfig.getAppId());

        config.setLastUpdatedTime(System.currentTimeMillis());
        configRepository.update(config);

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(dbConfig,
                ConfigChangedHistory.ACTION_UPDATE, operator)
                .setOriginal(configToJson(dbConfig))
                .setChanged(configToJson(config));
        configChangedHistoryRepository.save(configChangedHistory);

        // 配置更新通知
        applicationEventPublisher.publishEvent(new ConfigChangedEvent(app));
    }

    @Override
    public void delete(int id, String operator) {
        Config config = configRepository.findById(id);
        delete0(config, operator);

        // 配置更新通知
        App app = appRepository.findById(config.getAppId());
        applicationEventPublisher.publishEvent(new ConfigChangedEvent(app));
    }

    @Override
    public void deleteByAppId(int appId, String operator) {
        List<Config> configs = findByAppId(appId);
        for (Config config : configs) {
            delete0(config, operator);
        }

        // 配置更新通知
        App app = appRepository.findById(appId);
        applicationEventPublisher.publishEvent(new ConfigChangedEvent(app));
    }

    @Transactional(readOnly = true)
    @Override
    public Config findById(int id) {
        return configRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Config> findByAppId(int appId) {
        return configRepository.findByAppId(appId);
    }

    @Override
    public List<ConfigDto> findByAppNameAndEnv(String appName, Env appEnv) throws AppNotFoundException {
        App app = appRepository.findByNameAndEnv(appName, appEnv);
        if (app == null) {
            throw new AppNotFoundException("未找到应用 [name:" + appName + ", env:" + appEnv + "]");
        }

        // TODO: 预处理配置属性值
        List<Config> configs = findByAppId(app.getId());
        List<ConfigDto> list = new ArrayList<>(configs.size());
        ConfigDto configDto;
        for (Config config : configs) {
            configDto = new ConfigDto();
            configDto.setKey(config.getKey());
            configDto.setValue(config.getValue());
            configDto.setDescription(config.getDescription());
            list.add(configDto);
        }
        return list;
    }

    private void delete0(Config config, String operator) {
        configRepository.deleteById(config.getId());

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(config,
                ConfigChangedHistory.ACTION_DELETE, operator)
                .setOriginal(configToJson(config));
        configChangedHistoryRepository.save(configChangedHistory);
    }

    private String configToJson(Config config) {
        return ObjectMapperUtils.writeValueAsString(config);
    }

    private ConfigChangedHistory newConfigChangedHistory(Config config, String action, String operator) {
        App app = appRepository.findById(config.getAppId());
        ConfigChangedHistory configChangedHistory = new ConfigChangedHistory();
        return configChangedHistory.setAppName(app.getName())
                .setAppEnv(app.getEnv())
                .setConfigId(config.getId())
                .setAction(action)
                .setOperator(operator)
                .setCreatedTime(System.currentTimeMillis());
    }
}
