package com.myrpc.core.registry;

import java.net.InetSocketAddress;

/**
 * @author tangyang9464
 */
public interface ServiceRegistry {
    void register(String serviceName, InetSocketAddress serviceAddress);
}
