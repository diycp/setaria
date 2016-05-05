package com.weghst.setaria.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weghst.setaria.core.domain.UserApp;

/**
 * 用户数据访问接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface UserAppRepository {

    /**
     * 保存用户应用关系.
     *
     * @param userApp 用户应用关系
     * @return 受影响行记录数
     */
    int save(UserApp userApp);

    /**
     * 保存用户应用关系.
     *
     * @param userApps 用户应用关系
     * @return 受影响行记录数
     */
    int saveUserApps(List<UserApp> userApps);

    /**
     * 根据用户 ID删除用户应用关系.
     *
     * @param userId 用户 ID
     * @return 受影响行记录数
     */
    int deleteByUserId(int userId);

    /**
     * 根据应用 ID删除用户应用关系.
     *
     * @param appId 应用 ID
     * @return 受影响行记录数
     */
    int deleteByAppId(int appId);

    /**
     * 根据用户 ID 批量删除与应用的关系.
     *
     * @param userId 用户 ID
     * @param appIds 应用 ID 列表
     * @return 受影响行记录数
     */
    int deleteUserApps(@Param("userId") int userId, @Param("appIds") int[] appIds);

    /**
     * 根据应用 ID 批量删除与用户的关系.
     *
     * @param appId   应用 ID
     * @param userIds 用户 ID 列表
     * @return 受影响行记录数
     */
    int deleteAppUsers(@Param("appId") int appId, @Param("userIds") int[] userIds);

    /**
     * 根据用户 ID 查询用户所管理的应用 ID 列表.
     *
     * @param userId 用户 ID
     * @return 应用 ID 列表
     */
    List<Integer> findUserAppIds(int userId);

    /**
     * 根据应用 ID 查询应用与管理用户 ID 列表.
     *
     * @param appId 应用 ID
     * @return 用户 ID 列表
     */
    List<Integer> findAppUserIds(int appId);
}
