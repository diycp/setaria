package com.weghst.setaria.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.domain.UserApp;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface UserRepository {

    /**
     * @param user
     */
    void save(User user);

    /**
     * @param user
     */
    void update(User user);

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
     * @param manager
     * @return
     */
    List<User> findUserByManager(boolean manager);

    /**
     * @return
     */
    List<User> findAll();
}
