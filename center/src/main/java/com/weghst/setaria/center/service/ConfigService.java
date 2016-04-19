package com.weghst.setaria.center.service;

import com.weghst.setaria.center.domain.Config;

import java.util.List;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface ConfigService {

    /**
     *
     * @param config
     */
    void save(Config config);

    /**
     *
     * @param config
     */
    void update(Config config);

    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     *
     * @param appId
     * @return
     */
    List<Config> findByApp(int appId);

    /**
     *
     * @param appName
     * @param appEnv
     * @return
     */
    List<Config> findByAppNameAndAppEnv(String appName, String appEnv);
}
