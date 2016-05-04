package com.weghst.setaria.core.service;


import java.util.List;

import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.dto.ClientInfo;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface AppService {

    /**
     * @param app
     */
    void save(App app);

    /**
     * @param app
     * @param userIds
     */
    void save(App app, int[] userIds);

    /**
     * @param app
     */
    void update(App app);

    /**
     * @param app
     * @param userIds
     */
    void update(App app, int[] userIds);

    /**
     * @param id
     * @param operator
     */
    void deleteById(int id, String operator);

    /**
     * @param id
     * @return
     */
    App findById(int id);

    /**
     * @param userId
     * @return
     */
    List<App> findAppsByUserId(int userId);

    /**
     * @param userId
     * @return
     */
    List<App> findAppsByUserIdOrRole(int userId);

    /**
     * @param appId
     * @return
     */
    int[] findAppUserIds(int appId);

    /**
     * @return
     */
    List<App> findAll();

    /**
     * 根据应用 ID 加载客户端加载配置信息状态.
     *
     * @param id 应用 ID
     * @return 客户端加载配置信息状态
     */
    List<ClientInfo> loadClientInfo(int id);
}
