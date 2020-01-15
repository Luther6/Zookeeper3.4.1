package com.luther.operation;

import com.luther.zookeeper.ZkUtil;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author liujie
 * @date 2020/1/11 20:16
 */
public class ChangeNode {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zkClient = ZkUtil.getZkClient();
        createNode(zkClient);
        System.in.read();
    }
    public static void createNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        //创建一个永久的顺序的节点。 ACL为空
        zooKeeper.create("/luther2","hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
    }
}
