package com.myrpc.registry;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface ServiceDiscover {
    InetSocketAddress discoverService(String serviceName) throws Exception;
}
