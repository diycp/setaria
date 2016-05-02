package com.weghst.setaria.core.service;

import java.util.List;

import com.weghst.setaria.core.domain.Config;

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
}
