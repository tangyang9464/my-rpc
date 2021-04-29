package com.myrpc.http;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: tangyang9464
 * @create: 2021-04-28 11:43
 **/
@Data
@Accessors(chain = true)
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class<?>[] paramType;
    private Object[] param;
}
