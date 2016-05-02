package com.weghst.setaria.core.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.App;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface AppRepository {

    /**
     * @param app
     */
    void save(App app);

    /**
     * @param app
     */
    void update(App app);

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
     * @return
     */
    List<App> findAll();

}
