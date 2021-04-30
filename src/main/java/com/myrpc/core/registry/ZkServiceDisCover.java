package com.myrpc.core.registry;

import com.myrpc.core.loadbalance.LoadBalance;
import com.myrpc.core.loadbalance.loadbalacer.RandomBalancer;
import com.myrpc.core.registry.zk.CuratorUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: tangyang9464
 * @create: 2021-04-29 16:20
 **/
public class ZkServiceDisCover implements ServiceDiscover{
    private final LoadBalance loadBalancer;

    public ZkServiceDisCover(){
        this.loadBalancer=new RandomBalancer();
    }

    public ZkServiceDisCover(LoadBalance loadBalancer){
        this.loadBalancer=loadBalancer;
    }

    public InetSocketAddress discoverService(String serviceName){
        InetSocketAddress ans = null;
        try {
            List<String> serviceUrl = CuratorUtil.getChildrenNodes(serviceName);
            //负载均衡
            String foundRrl = loadBalancer.selectServiceAddress(serviceUrl);
            if(foundRrl!=null){
                String[] socketAddressArray = foundRrl.split(":");
                String host = socketAddressArray[0];
                int port = Integer.parseInt(socketAddressArray[1]);
                ans = new InetSocketAddress(host,port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
