package com.weghst.setaria.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.domain.UserApp;
import com.weghst.setaria.core.repository.AppRepository;
import com.weghst.setaria.core.repository.UserAppRepository;
import com.weghst.setaria.core.repository.UserRepository;
import com.weghst.setaria.core.service.AppService;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Transactional
@Service
public class AppServiceImpl implements AppService {

    private static final Logger LOG = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private UserRepository userRepository;

    // -------------------------------------------------
    @Value("${setaria.zookeeper.servers}")
    private String zookeeperServers;
    @Value("${setaria.zookeeper.basePath}")
    private String zookeeperBasePath;
    @Value("${setaria.pull.config.url}")
    private String pullConfigUrl;

    private CuratorFramework curatorFramework;

    @PostConstruct
    public void init() {
        curatorFramework = CuratorFrameworkFactory.newClient(zookeeperServers, new RetryOneTime(3000));
        curatorFramework.create();
        // FIXME
    }

    @PreDestroy
    public void destroy() {
        if (curatorFramework != null) {
            curatorFramework.close();
        }
    }

    @Override
    public boolean inform(App app) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryOneTime(1000));
        curatorFramework.start();

        final NodeCache nodeCache = new NodeCache(curatorFramework, buildPath(app), false);
        try {
            nodeCache.close();
            nodeCache.start(true);
            final AtomicInteger seq = new AtomicInteger(0);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println(nodeCache.getCurrentData().getPath());
                    String data = new String(nodeCache.getCurrentData().getData());
                    System.out.println("New Data: " + data + ", " + seq.getAndIncrement());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(zookeeperBasePath);
            Stat stat = curatorFramework.checkExists().forPath(buildPath(app));
            if (stat == null) {
                curatorFramework.create().forPath(buildPath(app));
            }
            for (int i = 0; i < 10; i++) {
                stat = curatorFramework.setData().forPath(buildPath(app), ("test" + i).getBytes());
                // Thread.sleep(1 * 1000);
            }
            System.out.println(stat);

//            curatorFramework.delete().forPath(buildPath(app));

            Thread.sleep(2 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(App app) {
        app.setCreatedTime(System.currentTimeMillis());
        appRepository.save(app);
    }

    @Override
    public void save(App app, int[] userIds) {
        save(app);
        saveUserApps(app.getId(), userIds);
    }

    @Override
    public void update(App app) {
        app.setLastUpdatedTime(System.currentTimeMillis());
        appRepository.update(app);
    }

    @Override
    public void update(App app, int[] userIds) {
        update(app);

        // 更新 APP 所属用户
        int[] dbUserIds = findAppUserIds(app.getId());
        int[] addUserIds = ArrayUtils.removeElements(userIds, dbUserIds);
        int[] deleteUserIds = ArrayUtils.removeElements(dbUserIds, userIds);

        saveUserApps(app.getId(), addUserIds);
        if (deleteUserIds.length > 0) {
            userAppRepository.deleteAppUsers(app.getId(), deleteUserIds);
        }
    }

    @Override
    public void deleteById(int id) {
        appRepository.deleteById(id);
        userAppRepository.deleteByAppId(id);
        // 删除 t_config 参数配置
    }

    @Transactional(readOnly = true)
    @Override
    public App findById(int id) {
        return appRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<App> findAppsByUserId(int userId) {
        return appRepository.findAppsByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<App> findAppsByUserIdOrRole(int userId) {
        User user = userRepository.findById(userId);
        if (user.isManager()) {
            return findAll();
        }
        return findAppsByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public int[] findAppUserIds(int appId) {
        List<Integer> list = userAppRepository.findAppUserIds(appId);
        return ArrayUtils.toPrimitive(list.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY));
    }

    @Transactional(readOnly = true)
    @Override
    public List<App> findAll() {
        return appRepository.findAll();
    }

    // FIXME
    private String buildPath(App app) {
        StringBuilder sb = new StringBuilder("/com/weghst/setaria/");
        sb.append(app.getName()).append("/").append(app.getEnv());
        return sb.toString();
    }

    @SuppressWarnings("Duplicates")
    private void saveUserApps(int appId, int[] userIds) {
        if (userIds.length == 0) {
            return;
        }

        List<UserApp> userApps = new ArrayList<>();
        for (int userId : userIds) {
            UserApp userApp = new UserApp();
            userApp.setUserId(userId);
            userApp.setAppId(appId);
            userApps.add(userApp);
        }
        userAppRepository.saveUserApps(userApps);
    }
}
