package com.myrpc.http;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: tangyang9464
 * @create: 2021-04-28 12:06
 **/
@Data
public class RpcResponse<T> implements Serializable {
    private T data;
    public static <T> RpcResponse<T> success(T data){
        RpcResponse<T> rpcResponse = new RpcResponse<T>();
        rpcResponse.setData(data);
        return rpcResponse;
    }
}
