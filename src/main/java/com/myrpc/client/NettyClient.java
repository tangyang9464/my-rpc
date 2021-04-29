package com.myrpc.client;

import com.myrpc.http.RpcRequest;
import com.myrpc.serialize.HessianDecoder;
import com.myrpc.serialize.HessianEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author: tangyang9464
 * @create: 2021-04-27 16:07
 **/
@Slf4j
public class NettyClient {
    private static final Bootstrap bootstrap;
    private static NioEventLoopGroup group;

    static{
        bootstrap = new Bootstrap();
         group = new NioEventLoopGroup();
        final ClientHandler clientHandler = new ClientHandler();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new HessianDecoder())
                                .addLast(new HessianEncoder())
                                .addLast(clientHandler);
                    }
                });
    }

    public Object sendRequest(RpcRequest req, String host,int port) throws InterruptedException {
        try{
            //连接
            ChannelFuture f = bootstrap.connect(host,port).sync();
            Channel channel = f.channel();
            if(channel!=null){
//                发送
                channel.writeAndFlush(req).addListener((res)->{
                    if(res.isSuccess()){
                        log.info("发送成功");
                    }
                    else{
                        log.info("发送错误:"+res.cause());
                    }
                });
                //关闭通道
                channel.closeFuture().sync();
                //获取结果
                AttributeKey<Object> key = AttributeKey.valueOf("response");
                return channel.attr(key).get();
            }
            return null;
        }finally {
            //关闭客户端
            group.shutdownGracefully();
        }
    }
}
