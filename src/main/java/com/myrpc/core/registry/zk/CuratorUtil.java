package com.myrpc.core.registry.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * @author: tangyang9464
 * @create: 2021-04-29 16:08
 **/
public class CuratorUtil {
    private static String connectString="127.0.0.1:2181";
    private static CuratorFramework zkClient = CuratorFrameworkFactory.newClient(connectString,new RetryNTimes(Integer.MAX_VALUE, 1000));
    public static String ZK_REGISTER_ROOT_PATH="/my-rpc/api";

    public static CuratorFramework getZkClient(){
        return zkClient;
    }
    public static void createPersistentNode(String path){
        try {
            zkClient.start();
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<String> getChildrenNodes(String serviceName) throws Exception {
        zkClient.start();
        String path = CuratorUtil.ZK_REGISTER_ROOT_PATH + "/" + serviceName;
        return zkClient.getChildren().forPath(path);
    }
}
