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
import org.apache.zookeeper.data.Stat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.prefs.NodeChangeEvent;
import java.util.prefs.NodeChangeListener;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class Test {

    public static void main(String[] args) {
        try {
//            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryPolicy() {
//                @Override
//                public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
//                    System.err.println("ERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
//                    return false;
//                }
//            });

            final ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType());
                }
            });

            zooKeeper.exists("/com/weghst/setaria/pine/developer", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    Event.EventType eventType = event.getType();
                    System.out.println(event);

                    try {
                        Stat stat = new Stat();
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println(stat);
                        byte[] bytes = zooKeeper.getData(event.getPath(), this, stat);
                        System.out.println(new String(bytes));
                        System.out.println(stat);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
//            zooKeeper.close();
//            zooKeeper.getChildren("/com/weghst/setaria/pine/developer", new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//                    System.out.println("++++++++++++++++++++++++++++++++++++++++");
//                    System.out.println(event);
//                }
//            });
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
