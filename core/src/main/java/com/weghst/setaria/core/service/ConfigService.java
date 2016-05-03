package com.weghst.setaria.core.service;

import java.util.List;

import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.domain.Env;
import com.weghst.setaria.core.dto.ConfigDto;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface ConfigService {

    /**
     * @param config
     * @param operator
     */
    void save(Config config, String operator);

    /**
     * @param config
     * @param operator
     */
    void update(Config config, String operator);

    /**
     * @param id
     * @param operator
     */
    void delete(int id, String operator);

    /**
     * @param id
     * @return
     */
    Config findById(int id);

    /**
     * @param appId
     * @return
     */
    List<Config> findByAppId(int appId);

    /**
     *
     * @param appName
     * @param appEnv
     * @return
     */
    List<ConfigDto> findByAppNameAndEnv(String appName, Env appEnv) throws AppNotFoundException;
}
