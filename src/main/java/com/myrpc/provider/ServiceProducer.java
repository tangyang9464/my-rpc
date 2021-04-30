package com.myrpc.provider;

import com.myrpc.core.registry.ZkServiceRegistry;
import com.myrpc.core.registry.zk.CuratorUtil;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: tangyang9464
 * @create: 2021-04-30 10:35
 **/
public class ServiceProducer {
    private static Map<String,Object> serviceMap;

    public <T> void publish(Class<T> clazz, int port) throws Exception{
        String serviceName = clazz.getInterfaces()[0].getName();
        ZkServiceRegistry registry = new ZkServiceRegistry();
        registry.register(serviceName,new InetSocketAddress("127.0.0.1",port));
        if(serviceMap==null){
            serviceMap = new HashMap<>();
        }
        serviceMap.put(serviceName,clazz.getDeclaredConstructor().newInstance());
    }
    public Object getService(String serviceName){
        return serviceMap.getOrDefault(serviceName,null);
    }

    public void clearAllService(){
        if(serviceMap==null){
            return;
        }
        Set<String> services = serviceMap.keySet();
        for(String serviceName:services){
            clearService(serviceName);
        }
    }

    public void clearService(String serviceName) {
        CuratorUtil.deleteNode(serviceName);
    }
}
