package com.myrpc.core.registry;

import java.net.InetSocketAddress;

public interface ServiceDiscover {
    InetSocketAddress discoverService(String serviceName) throws Exception;
}
