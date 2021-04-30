package com.myrpc.core.remoting.transport.netty.server;

import com.myrpc.core.remoting.dto.RpcRequest;
import com.myrpc.core.remoting.dto.RpcResponse;
import com.myrpc.provider.ServiceProducer;
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
        ServiceProducer serviceProducer = new ServiceProducer();
        Object service = serviceProducer.getService(rpcRequest.getInterfaceName());
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamType());
        Object data = method.invoke(service,rpcRequest.getParam());

        ctx.writeAndFlush(RpcResponse.success(data));
        ctx.writeAndFlush(msg);
     }
}
