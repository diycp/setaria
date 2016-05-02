package com.weghst.setaria.core.service.impl;

import java.util.List;

import javax.json.JsonException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.domain.ConfigChangedHistory;
import com.weghst.setaria.core.repository.AppRepository;
import com.weghst.setaria.core.repository.ConfigChangedHistoryRepository;
import com.weghst.setaria.core.repository.ConfigRepository;
import com.weghst.setaria.core.service.ConfigService;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ConfigChangedHistoryRepository configChangedHistoryRepository;

    @Override
    public void save(Config config, String operator) {
        config.setCreatedTime(System.currentTimeMillis());
        configRepository.save(config);

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(config,
                ConfigChangedHistory.ACTION_INSERT, operator)
                .setConfigId(config.getId())
                .setChanged(configToJson(config));
        configChangedHistoryRepository.save(configChangedHistory);
    }

    @Override
    public void update(Config config, String operator) {
        Config dbConfig = configRepository.findById(config.getId());

        config.setLastUpdatedTime(System.currentTimeMillis());
        configRepository.update(config);

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(dbConfig,
                ConfigChangedHistory.ACTION_UPDATE, operator)
                .setOriginal(configToJson(dbConfig))
                .setChanged(configToJson(config));
        configChangedHistoryRepository.save(configChangedHistory);
    }

    @Override
    public void delete(int id, String operator) {
        Config dbConfig = configRepository.findById(id);

        configRepository.deleteById(id);

        // 保存操作历史
        ConfigChangedHistory configChangedHistory = newConfigChangedHistory(dbConfig,
                ConfigChangedHistory.ACTION_DELETE, operator)
                .setOriginal(configToJson(dbConfig));
        configChangedHistoryRepository.save(configChangedHistory);
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

    private String configToJson(Config config) {
        try {
            return objectMapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            LOG.error("Config 转换 json 错误", e);
            throw new JsonException("Config 转换 json 错误", e);
        }
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
