package com.myrpc.testClient;

import com.myrpc.api.TestService;
import com.myrpc.core.loadbalance.loadbalacer.RandomBalancer;
import com.myrpc.proxy.ClientProxy;

/**
 * @author: tangyang9464
 * @create: 2021-04-24 12:18
 **/
public class ClientMain {
    public static void main(String[] args){
        TestService helloService = (TestService) ClientProxy.getInstance(TestService.class,new RandomBalancer());
        System.out.println(helloService.test());
    }
}
