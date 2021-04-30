package com.myrpc.core.loadbalance.loadbalacer;

import com.myrpc.core.loadbalance.LoadBalance;

import java.util.List;

/**
 * @author: tangyang9464
 * @create: 2021-04-30 10:02
 **/
public class RoundRobinLoadBalancer implements LoadBalance {
    private static int index;

    @Override
    public String selectServiceAddress(List<String> urlArr) {
        if(urlArr==null || urlArr.size()==0){
            return null;
        }
        return urlArr.get((index+1)%urlArr.size());
    }
}
