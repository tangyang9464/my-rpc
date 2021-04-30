package com.myrpc.testServer;

import com.myrpc.core.remoting.transport.netty.server.NettyServer;
import com.myrpc.provider.ServiceProducer;

/**
 * @author: tangyang9464
 * @create: 2021-04-24 12:20
 **/
public class ServerMain {
    public static void main(String[] args) throws Exception{
        NettyServer nettyServer = new NettyServer();
        ServiceProducer serviceProducer = new ServiceProducer();
        int port = 9191;
        //发布服务
        serviceProducer.publish(TestServiceImpl.class,port);
        //启动网络传输server
        nettyServer.run(port);
    }
}
