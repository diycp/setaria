package com.weghst.setaria.core.service;

import java.util.List;

import com.weghst.setaria.core.domain.User;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface UserService {

    /**
     * @param user
     */
    void save(User user);

    /**
     * @param user
     * @param appIds
     */
    void save(User user, int[] appIds);

    /**
     * @param user
     */
    void update(User user);

    /**
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(int id, String oldPassword, String newPassword);

    /**
     * @param user
     * @param appIds
     */
    void update(User user, int[] appIds);

    /**
     * @param id
     */
    void deleteById(int id);

    /**
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * @param id
     * @return
     */
    int[] findUserAppIds(int id);

    /**
     * @return
     */
    List<User> findOrdinaryUsers();

    /**
     * @return
     */
    List<User> findAll();

    /**
     * @param email
     * @param password
     * @return
     */
    User authenticate(String email, String password);
}
