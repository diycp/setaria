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
     * @param manager 是否为管理员
     * @return 用户信息列表
     */
    List<User> findUserByManager(boolean manager);

    /**
     * 查询所有用户信息.
     *
     * @return 用户信息列表
     */
    List<User> findAll();
}
