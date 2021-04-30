package com.myrpc.core.registry;

import com.myrpc.core.registry.zk.CuratorUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;


/**
 * @author: tangyang9464
 * @create: 2021-04-29 12:01
 **/
@Slf4j
public class ZkServiceRegistry implements ServiceRegistry{
    public void register(String serviceName, InetSocketAddress serviceAddress) {
        String path = CuratorUtil.ZK_REGISTER_ROOT_PATH + serviceName + serviceAddress.toString();
        CuratorUtil.createPersistentNode(path);
    }
}
