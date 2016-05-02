package com.weghst.setaria.core.repository;

import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.ConfigChangedHistory;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface ConfigChangedHistoryRepository {

    /**
     * @return
     */
    int save(ConfigChangedHistory configChangedHistory);
}
