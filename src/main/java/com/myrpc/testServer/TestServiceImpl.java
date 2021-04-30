package com.myrpc.testServer;

import com.myrpc.api.TestService;

/**
 * @author: tangyang9464
 * @create: 2021-04-30 10:43
 **/
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }
}
