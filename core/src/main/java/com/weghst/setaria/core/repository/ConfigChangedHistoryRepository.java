package com.weghst.setaria.core.repository;

import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.ConfigChangedHistory;

/**
 * 配置修改历史数据库访问接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface ConfigChangedHistoryRepository {

    /**
     * 保存配置修改历史信息.
     *
     * @param configChangedHistory 配置修改历史
     * @return 受影响行记录数
     */
    int save(ConfigChangedHistory configChangedHistory);
}
