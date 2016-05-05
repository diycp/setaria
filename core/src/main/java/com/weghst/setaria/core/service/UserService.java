package com.weghst.setaria.core.service;

import java.util.List;

import com.weghst.setaria.core.domain.User;

/**
 * 用户逻辑接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface UserService {

    /**
     * 保存用户信息.
     *
     * @param user 用户信息
     */
    void save(User user);

    /**
     * 保存用户信息并同时关联用户与应用的关系.
     *
     * @param user   用户信息
     * @param appIds 应用 ID
     */
    void save(User user, int[] appIds);

    /**
     * 更新用户信息. 禁止修改 ROOT 用户.
     *
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 更新用户密码.
     *
     * @param id          用户 ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(int id, String oldPassword, String newPassword);

    /**
     * 更新用户信息并同时更新用户与应用的关系. 禁止修改 ROOT 用户.
     *
     * @param user   用户信息
     * @param appIds 应用 ID
     */
    void update(User user, int[] appIds);

    /**
     * 根据用户 ID 删除用户信息. 禁止删除 ROOT 用户.
     *
     * @param id 用户 ID
     */
    void deleteById(int id);

    /**
     * 根据用户 ID 查询用户信息.
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    User findById(int id);

    /**
     * 根据用户邮箱查询用户信息.
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User findByEmail(String email);

    /**
     * 根据用户 ID 查询所管理的应用 ID 列表.
     *
     * @param id 用户 ID
     * @return 应用 ID 列表
     */
    int[] findUserAppIds(int id);

    /**
     * 查询普通用户信息列表.
     *
     * @return 用户信息列表
     */
    List<User> findOrdinaryUsers();

    /**
     * 查询所有用户信息.
     *
     * @return 用户信息列表
     */
    List<User> findAll();

    /**
     * 根据邮箱密码认证用户信息.
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @return 用户信息
     * @throws UserNotFoundException       如果不存在对应邮箱的用户
     * @throws PasswordNotMatchedException 如果密码不匹配
     */
    User authenticate(String email, String password) throws UserNotFoundException, PasswordNotMatchedException;
}
