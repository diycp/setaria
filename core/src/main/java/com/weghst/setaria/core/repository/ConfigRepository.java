package com.weghst.setaria.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.Config;

/**
 * 配置数据库访问接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface ConfigRepository {

    /**
     * 保存配置信息.
     *
     * @param config 配置信息
     * @return 受影响行记录数
     */
    int save(Config config);

    /**
     * 更新配置信息.
     *
     * @param config 配置信息
     * @return 受影响行记录数
     */
    int update(Config config);

    /**
     * 根据 ID 删除配置信息.
     *
     * @param id 配置 ID
     * @return 受影响行记录数
     */
    int deleteById(int id);

    /**
     * 根据 ID 查询配置信息.
     *
     * @param id 配置 ID
     * @return 配置信息
     */
    Config findById(int id);

    /**
     * 根据应用 ID 与配置键查询配置信息.
     *
     * @param appId 应用 ID
     * @param key   配置键
     * @return 配置信息
     */
    Config findByAppIdAndKey(@Param("appId") int appId, @Param("key") String key);

    /**
     * 根据应用 ID 查询所属配置信息列表.
     *
     * @param appId 应用 ID
     * @return 配置信息列表
     */
    List<Config> findByAppId(int appId);
}
