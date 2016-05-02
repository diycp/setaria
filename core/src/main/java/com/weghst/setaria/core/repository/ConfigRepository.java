package com.weghst.setaria.core.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.Config;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface ConfigRepository {

    /**
     * @param config
     * @return
     */
    int save(Config config);

    /**
     * @param config
     * @return
     */
    int update(Config config);

    /**
     * @param id
     * @return
     */
    int deleteById(int id);

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
