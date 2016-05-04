package com.weghst.setaria.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weghst.setaria.core.domain.*;
import com.weghst.setaria.core.repository.AppRepository;
import com.weghst.setaria.core.repository.ConfigRepository;
import com.weghst.setaria.core.repository.UserAppRepository;
import com.weghst.setaria.core.repository.UserRepository;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.ConfigChangedEvent;
import com.weghst.setaria.core.util.ZooKeeperException;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Transactional
@Service
public class AppServiceImpl implements AppService, ApplicationListener<ConfigChangedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(AppServiceImpl.class);

    private static final String PULL_CONFIG_URL_PATH = "/url";
    private static final String APPS_PATH = "/apps";

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfigRepository configRepository;

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
        curatorFramework.start();
        createNode(zookeeperBasePath, null);

        try {
            String urlPath = normalizePath(zookeeperBasePath + PULL_CONFIG_URL_PATH);
            Stat stat = curatorFramework.checkExists().forPath(urlPath);
            if (stat == null) {
                curatorFramework.create().forPath(urlPath, pullConfigUrl.getBytes());
                LOG.info("设置拉取配置URL [{}]", pullConfigUrl);
            } else {
                byte[] bytes = curatorFramework.getData().forPath(urlPath);
                String existsUrl = new String(bytes);
                if (!pullConfigUrl.equals(existsUrl)) {
                    curatorFramework.setData().forPath(urlPath, pullConfigUrl.getBytes());
                    LOG.info("拉取配置URL从 [{}] 更新至 [{}]", existsUrl, pullConfigUrl);
                }
            }
        } catch (Exception e) {
            throw new ZooKeeperException(e);
        }

        // 创建 apps 节点
        String appsPath = normalizePath(zookeeperBasePath + APPS_PATH);
        createNode(appsPath, null);

        // 创建应用节点
        List<App> apps = appRepository.findAll();
        for (App app : apps) {
            if (app.getVersion() != 0) {
                createAppNode(app);
            }
        }
    }

    private String normalizePath(String path) {
        String normalizedPath = path.replaceAll("//+", "/").replaceFirst("(.+)/$", "$1");
        PathUtils.validatePath(normalizedPath);
        return normalizedPath;
    }

    private void createNode(String path, byte[] bytes) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat == null) {
                if (bytes == null) {
                    curatorFramework.create().forPath(path);
                } else {
                    curatorFramework.create().forPath(path, bytes);
                }
            }
        } catch (Exception e) {
            throw new ZooKeeperException(e);
        }
    }

    private String createAppNode(App app) {
        String appPath = zookeeperBasePath + APPS_PATH + "/" + app.getName();
        String envPath = appPath + "/" + app.getEnv().name();
        createNode(appPath, null);
        createNode(envPath, null);
        return envPath;
    }

    private void updateAppNode(App app) {
        String appPath = zookeeperBasePath + APPS_PATH + "/" + app.getName();
        String envPath = appPath + "/" + app.getEnv().name();
        try {
            if (curatorFramework.checkExists().forPath(envPath) == null) {
                createAppNode(app);
            }
            String version = String.valueOf(app.getVersion());
            curatorFramework.setData().forPath(envPath, version.getBytes());
        } catch (Exception e) {
            throw new ZooKeeperException(e);
        }
    }

    private void deleteAppNode(App app) {
        String appPath = zookeeperBasePath + APPS_PATH + "/" + app.getName();
        String envPath = appPath + "/" + app.getEnv().name();
        try {
            curatorFramework.delete().forPath(envPath);
            curatorFramework.delete().forPath(appPath);
        } catch (Exception e) {
            // ignore
        }
    }

    @PreDestroy
    public void destroy() {
        if (curatorFramework != null) {
            try {
                String appPath = zookeeperBasePath + APPS_PATH;
                List<String> appNames = curatorFramework.getChildren().forPath(appPath);
                for (String appName : appNames) {
                    List<String> appEnvs = curatorFramework.getChildren().forPath(appPath + "/" + appName);
                    for (String appEnv : appEnvs) {
                        Env env = Env.valueOf(appEnv);
                        App app = appRepository.findByNameAndEnv(appName, env);
                        if (app == null) {
                            app = new App();
                            app.setName(appName);
                            app.setEnv(env);

                            // 删除 zookeeper 中的应用节点
                            deleteAppNode(app);
                        }
                    }
                }
            } catch (Exception e) {
                // FIXME
                LOG.error("{}", e);
            }

            curatorFramework.close();
        }
    }

    @Override
    public void onApplicationEvent(ConfigChangedEvent event) {
        App app = (App) event.getSource();

        // 更新版本号
        app.setVersion(System.currentTimeMillis() / 1000);
        appRepository.updateVersion(app.getId(), app.getVersion());

        updateAppNode(app);
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
        List<Config> configs = configRepository.findByAppId(id);
        // 如果应用下有配置项，不让其删除
        if (!configs.isEmpty()) {
            throw new IllegalStateException("删除应用 [" + id + "] 之前请先删除应用下所管理的配置");
        }

        appRepository.deleteById(id);
        userAppRepository.deleteByAppId(id);
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
