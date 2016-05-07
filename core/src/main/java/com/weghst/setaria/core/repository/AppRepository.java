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
package com.weghst.setaria.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.Env;

/**
 * 应用数据库访问接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface AppRepository {

    /**
     * 保存应用信息.
     *
     * @param app 应用信息
     * @return 受影响行记录数
     */
    int save(App app);

    /**
     * 更新应用信息.
     *
     * @param app 应用信息
     * @return 受影响行记录数
     */
    int update(App app);

    /**
     * 更新应用版本信息.
     *
     * @param id      应用 ID
     * @param version 新版本号
     * @return 受影响行记录数
     */
    int updateVersion(@Param("id") int id, @Param("version") long version);

    /**
     * 根据 ID 删除应用.
     *
     * @param id 应用 ID
     * @return 受影响行记录数
     */
    int deleteById(int id);

    /**
     * 根据 ID 查询应用信息.
     *
     * @param id 应用 ID
     * @return 应用信息
     */
    App findById(int id);

    /**
     * 根据名称与环境查询应用信息.
     *
     * @param name 应用名称
     * @param env  应用环境
     * @return 应用信息
     */
    App findByNameAndEnv(@Param("name") String name, @Param("env") Env env);

    /**
     * 根据用户 ID 查询所管理的应用信息列表.
     *
     * @param userId 用户 ID
     * @return 应用信息列表
     */
    List<App> findAppsByUserId(int userId);

    /**
     * 查询所有应用信息.
     *
     * @return 应用信息列表
     */
    List<App> findAll();

}
