package com.luther.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujie
 * @date 2020/1/8 23:13
 */
public class ZookeeperClientTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("连接时的Event   -------  " + event);
            }
        });
        Stat stat = new Stat();

        zooKeeper.getData("/luther", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(Event.EventType.NodeDataChanged.equals(event.getType())){
                    System.out.println("节点内容已经改变了");
                }
            }
        },stat);
        System.out.println("------------------");
        System.out.println(stat);



        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(stat);
                    System.out.println("------------------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.in.read();
    }

}
