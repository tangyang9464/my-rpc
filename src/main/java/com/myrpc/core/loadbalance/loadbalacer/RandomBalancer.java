package com.myrpc.core.loadbalance.loadbalacer;

import com.myrpc.core.loadbalance.LoadBalance;

import java.util.List;
import java.util.Random;

/**
 * @author: tangyang9464
 * @create: 2021-04-30 09:59
 **/
public class RandomBalancer implements LoadBalance {
    @Override
    public String selectServiceAddress(List<String> urlArr) {
        if(urlArr==null || urlArr.size()==0){
            return null;
        }
        return urlArr.get(new Random().nextInt(urlArr.size()));
    }
}
