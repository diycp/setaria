package com.weghst.setaria.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.UserApp;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface UserAppRepository {

    /**
     * @param userApp
     */
    int save(UserApp userApp);

    /**
     * @param userApps
     */
    int saveUserApps(List<UserApp> userApps);

    /**
     * @param userId
     */
    int deleteByUserId(int userId);

    /**
     * @param appId
     */
    int deleteByAppId(int appId);

    /**
     * @param userId
     * @param appIds
     */
    int deleteUserApps(@Param("userId") int userId, @Param("appIds") int[] appIds);

    /**
     * @param appId
     * @param userIds
     * @return
     */
    int deleteAppUsers(@Param("appId") int appId, @Param("userIds") int[] userIds);

    /**
     * @param userId
     * @return
     */
    List<Integer> findUserAppIds(int userId);

    /**
     * @param appId
     * @return
     */
    List<Integer> findAppUserIds(int appId);
}
