package com.myrpc.testServer;


import com.myrpc.api.HelloService;

/**
 * @author: tangyang9464
 * @create: 2021-04-19 20:34
 **/
public class HelloServiceImpl implements HelloService {
    public String say() {
        return "hello";
    }
}
