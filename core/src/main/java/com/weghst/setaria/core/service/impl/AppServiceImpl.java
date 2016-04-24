package com.weghst.setaria.core.service.impl;

import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.Env;
import com.weghst.setaria.core.service.AppService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Service
public class AppServiceImpl implements AppService {

    private static final Logger LOG = LoggerFactory.getLogger(AppServiceImpl.class);

    public static void main(String[] args) {
        AppServiceImpl appService = new AppServiceImpl();
        App app = new App();
        app.setName("pine");
        app.setEnv(Env.developer);

        appService.inform(app);
    }

    @Override
    public boolean inform(App app) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryPolicy() {
            @Override
            public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                LOG.error("ZooKeeper 连接断开");
                return false;
            }
        });
        curatorFramework.start();

        final NodeCache nodeCache = new NodeCache(curatorFramework, buildPath(app), false);
        try {
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
            Stat stat = curatorFramework.checkExists().forPath(buildPath(app));
            if (stat == null) {
                curatorFramework.create().forPath(buildPath(app));
            }
            for (int i = 0; i < 10; i++) {
                stat = curatorFramework.setData().forPath(buildPath(app), ("test" + i).getBytes());
            }
            System.out.println(stat);

//            curatorFramework.delete().forPath(buildPath(app));

            Thread.sleep(2 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String buildPath(App app) {
        StringBuilder sb = new StringBuilder("/com/weghst/setaria/");
        sb.append(app.getName()).append("/").append(app.getEnv());
        return sb.toString();
    }
}
