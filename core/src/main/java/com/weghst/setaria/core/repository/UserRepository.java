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

import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.User;

/**
 * 用户数据库访问接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface UserRepository {

    /**
     * 保存用户信息.
     *
     * @param user 用户信息
     * @return 受影响行记录数
     */
    int save(User user);

    /**
     * 修改用户信息.
     *
     * @param user 用户信息
     * @return 受影响行记录数
     */
    int update(User user);

    /**
     * 根据 ID 删除用户信息.
     *
     * @param id 用户 ID
     * @return 受影响行记录数
     */
    int deleteById(int id);

    /**
     * 根据 ID 查询用户信息.
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    User findById(int id);

    /**
     * 根据邮箱查询用户信息.
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User findByEmail(String email);

    /**
     * 根据用户类型查询用户信息列表.
     *
     * @param type 是否为管理员
     * @return 用户信息列表
     */
    List<User> findUserByManager(String type);

    /**
     * 查询所有用户信息.
     *
     * @return 用户信息列表
     */
    List<User> findAll();
}
