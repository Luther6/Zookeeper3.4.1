package com.luther.operation;

import com.luther.zookeeper.ZkUtil;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liujie
 * @date 2020/1/11 20:16
 */
public class ChangeNode {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zkClient = ZkUtil.getZkClient();
        createDigestAclNode(zkClient);
        System.in.read();
    }
    public static void createNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        //创建一个永久的顺序的节点。 ACL为空
        zooKeeper.create("/luther2","hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
    }
    static void createIpAclNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        ACL acl = new ACL();
        Id id = new Id("ip","127.0.0.1");
        acl.setId(id);
        acl.setPerms(ZooDefs.Perms.ALL);
        create(zooKeeper, acl);
    }
    static void createDigestAclNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        ACL acl = new ACL();
        Id id = new Id("digest","anacr:6rlEUTJqzSRDscPDm0D/Drhk4FU=");
        acl.setId(id);
        acl.setPerms(11101);

        create(zooKeeper, acl);

    }

    private static void create(ZooKeeper zooKeeper, ACL acl) throws KeeperException, InterruptedException {
        List<ACL> acls = new ArrayList<>();
        acls.add(acl);

        String s = zooKeeper.create("/anacr6", "hello,xyw".getBytes(), acls, CreateMode.PERSISTENT);
        System.out.println(s);
    }

    static void showAcl(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        //添加登录信息
        zooKeeper.addAuthInfo("digest","anacr:qwe123".getBytes());

        List<ACL> acl = zooKeeper.getACL("/anacr5", zooKeeper.exists("/anacr5", false));
        acl.forEach(acl1 -> {
            System.out.println(acl1.getPerms());
            System.out.println(acl1.getId());
        });
    }

}
