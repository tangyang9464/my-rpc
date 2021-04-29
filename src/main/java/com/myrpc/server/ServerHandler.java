package com.myrpc.server;

import com.myrpc.http.RpcRequest;
import com.myrpc.http.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author: tangyang9464
 * @create: 2021-04-25 16:45
 **/
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("新客户端:"+ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("服务端接收到请求");
        RpcRequest rpcRequest = (RpcRequest)msg;
        HelloServiceImpl helloService = new HelloServiceImpl();
        Method method = helloService.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamType());
        Object data = method.invoke(helloService,rpcRequest.getParam());

        ctx.writeAndFlush(RpcResponse.success(data));
        ctx.writeAndFlush(msg);
     }
}
