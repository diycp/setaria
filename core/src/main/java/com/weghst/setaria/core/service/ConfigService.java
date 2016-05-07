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

import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.domain.Env;
import com.weghst.setaria.core.dto.ConfigDto;

/**
 * 配置逻辑接口定义.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface ConfigService {

    /**
     * 保存配置信息.
     *
     * @param config   配置信息
     * @param operator 操作员
     */
    void save(Config config, String operator);

    /**
     * 更新配置信息.
     *
     * @param config   配置信息
     * @param operator 操作员
     */
    void update(Config config, String operator);

    /**
     * 保存或者更新配置信息.
     *
     * @param configs  配置信息列表
     * @param operator 操作员
     */
    void saveOrUpdate(Config[] configs, String operator);

    /**
     * 删除配置信息.
     *
     * @param id       配置信息
     * @param operator 操作员
     */
    void delete(int id, String operator);

    /**
     * 根据应用 ID 删除应用下所有的配置信息.
     *
     * @param appId    应用 ID
     * @param operator 操作员
     */
    void deleteByAppId(int appId, String operator);

    /**
     * 根据配置 ID 查询配置详细.
     *
     * @param id 配置 ID
     * @return 配置详细
     */
    Config findById(int id);

    /**
     * 根据应用 ID 查询所有配置详细列表.
     *
     * @param appId 应用 ID
     * @return 配置详细列表
     */
    List<Config> findByAppId(int appId);

    /**
     * 根据应用名称与应用环境查询所有配置详细列表.
     *
     * @param appName 应用名称
     * @param appEnv  应用环境
     * @return 配置详细列表
     * @throws AppNotFoundException 如果应用不存在
     */
    List<ConfigDto> findByAppNameAndEnv(String appName, Env appEnv) throws AppNotFoundException;
}
