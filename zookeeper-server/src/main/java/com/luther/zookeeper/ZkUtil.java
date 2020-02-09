package com.luther.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liujie
 * @date 2020/1/11 20:17
 */
public class ZkUtil {
    public static ZooKeeper getZkClient() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 5000, event -> {
                if(Watcher.Event.KeeperState.SyncConnected.equals(event.getState())){
                    countDownLatch.countDown();
                    System.out.println("获取到了、链接");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.await(10, TimeUnit.SECONDS);
        return zooKeeper;
    }
}
