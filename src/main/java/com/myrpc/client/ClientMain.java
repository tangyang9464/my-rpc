package com.myrpc.client;

import com.myrpc.api.HelloService;

/**
 * @author: tangyang9464
 * @create: 2021-04-24 12:18
 **/
public class ClientMain {
    public static void main(String[] args){
        HelloService helloService = (HelloService) ClientProxy.getInstance(HelloService.class);
        System.out.println(helloService.say());
    }
}
