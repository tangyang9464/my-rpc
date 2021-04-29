package com.myrpc.registry;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author tangyang9464
 */
public interface ServiceRegistry {
    void register(String serviceName, InetSocketAddress serviceAddress);
}
