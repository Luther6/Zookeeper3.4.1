package com.luther.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author liujie
 * @date 2020/1/11 20:17
 */
public class ZkUtil {
    public static ZooKeeper getZkClient(){
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("连接时的Event   -------  " + event);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
