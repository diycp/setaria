/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.core.service;


import java.util.List;

import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.dto.ClientInfo;

/**
 * 应用逻辑接口定义.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface AppService {

    /**
     * 保存应用.
     *
     * @param app 应用
     */
    void save(App app);

    /**
     * 保存应用且添加关联应用所属用户.
     *
     * @param app     应用
     * @param userIds 用户 ID
     */
    void save(App app, int[] userIds);

    /**
     * 更新应用.
     *
     * @param app 应用
     */
    void update(App app);

    /**
     * 更新应用且更新关联应用所属用户.
     *
     * @param app     应用
     * @param userIds 用户 ID
     */
    void update(App app, int[] userIds);

    /**
     * 根据应用 ID 删除应用.
     *
     * @param id       应用 ID
     * @param operator 删除应用的操作员
     */
    void deleteById(int id, String operator);

    /**
     * 根据应用 ID 加载应用详细信息.
     *
     * @param id 应用 ID
     * @return 应用
     */
    App findById(int id);

    /**
     * 根据用户 ID 加载应用列表详细信息.
     *
     * @param userId 用户 ID
     * @return 应用列表
     */
    List<App> findAppsByUserId(int userId);

    /**
     * 根据用户 ID 或角色加载应用列表详细信息, 如果用户为管理员将返回所有应用列表,
     * 当用户为普通用户时则返回用户所管理的应用列表.
     *
     * @param userId 用户 ID
     * @return 应用列表
     */
    List<App> findAppsByUserIdOrRole(int userId);

    /**
     * 根据应用 ID 查询应用所属用户 ID.
     *
     * @param appId 应用 ID
     * @return 用户 ID
     */
    int[] findAppUserIds(int appId);

    /**
     * 返回所有应用列表.
     *
     * @return 应用列表
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
