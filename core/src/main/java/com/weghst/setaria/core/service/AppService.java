package com.weghst.setaria.core.service;


import java.util.List;

import com.weghst.setaria.core.domain.App;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface AppService {

    /**
     * @param app
     * @return
     */
    boolean inform(App app);

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
     */
    void deleteById(int id);

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
     *
     * @param appId
     * @return
     */
    int[] findAppUserIds(int appId);

    /**
     * @return
     */
    List<App> findAll();
}
