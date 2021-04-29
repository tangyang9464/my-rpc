package com.myrpc.client;

import com.myrpc.http.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: tangyang9464
 * @create: 2021-04-27 14:39
 **/
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<RpcResponse<?>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse<?> msg) throws Exception {
        log.info("客户端得到响应结果："+msg.getData());
        AttributeKey<Object> key = AttributeKey.valueOf("response");
        ctx.channel().attr(key).set(msg.getData());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
