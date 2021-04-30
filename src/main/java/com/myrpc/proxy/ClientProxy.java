package com.myrpc.proxy;


import com.myrpc.core.remoting.dto.RpcRequest;
import com.myrpc.core.loadbalance.LoadBalance;
import com.myrpc.core.registry.ZkServiceDisCover;
import com.myrpc.core.remoting.transport.netty.client.ClientHandler;
import com.myrpc.core.remoting.transport.netty.client.NettyClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @author: tangyang9464
 * @create: 2021-04-24 11:46
 **/
public class ClientProxy {
    private static final ClientHandler clientHandler = new ClientHandler();
    public static Object getInstance(final Class<?> clazz,LoadBalance loadBalancer){
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                NettyClient nettyClient = new NettyClient();
                //构建请求
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setInterfaceName(clazz.getName())
                        .setMethodName(method.getName())
                        .setParamType(method.getParameterTypes())
                        .setParam(args);
                //服务发现
                ZkServiceDisCover disCover = new ZkServiceDisCover(loadBalancer);
                InetSocketAddress adress = disCover.discoverService(clazz.getName());
                //发送请求
                return nettyClient.sendRequest(rpcRequest,adress.getHostName(),adress.getPort());
            }
        });
    }
}
