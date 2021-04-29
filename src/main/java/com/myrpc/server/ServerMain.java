package com.myrpc.server;

import com.myrpc.api.HelloService;

/**
 * @author: tangyang9464
 * @create: 2021-04-24 12:20
 **/
public class ServerMain {
    public static void main(String[] args) throws Exception{
        NettyServer nettyServer = new NettyServer();
        int port = 9090;
        nettyServer.publish(HelloService.class,port);
        nettyServer.run(port);
    }
}
