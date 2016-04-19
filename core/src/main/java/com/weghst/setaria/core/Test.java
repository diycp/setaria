package com.weghst.setaria.core;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.prefs.NodeChangeEvent;
import java.util.prefs.NodeChangeListener;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class Test {

    public static void main(String[] args) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryPolicy() {
                @Override
                public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                    System.err.println("ERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                    return false;
                }
            });

//            zooKeeper.getChildren("/com/weghst/setaria/pine/developer", new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//                    System.out.println("++++++++++++++++++++++++++++++++++++++++");
//                    System.out.println(event);
//                }
//            });

            Thread.sleep(100 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
