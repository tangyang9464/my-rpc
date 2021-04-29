package com.myrpc.registry;

import com.myrpc.registry.zk.CuratorUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author: tangyang9464
 * @create: 2021-04-29 16:20
 **/
public class ZkServiceDisCover implements ServiceDiscover{

    public InetSocketAddress discoverService(String serviceName){
        InetSocketAddress ans = null;
        try {
            List<String> serviceUrl = CuratorUtil.getChildrenNodes(serviceName);
            //负载均衡
            String[] socketAddressArray = serviceUrl.get(0).split(":");
            String host = socketAddressArray[0];
            int port = Integer.parseInt(socketAddressArray[1]);
            ans = new InetSocketAddress(host,port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
