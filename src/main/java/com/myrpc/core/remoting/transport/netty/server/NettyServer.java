package com.myrpc.core.remoting.transport.netty.server;

import com.myrpc.core.remoting.transport.netty.codec.HessianDecoder;
import com.myrpc.core.remoting.transport.netty.codec.HessianEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: tangyang9464
 * @create: 2021-04-27 15:58
 **/
@Slf4j
public class NettyServer {

    public void run(int port) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置轮询组
            bootstrap.group(boss, worker);
            //设置通道类型
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new HessianDecoder())
                            .addLast(new HessianEncoder())
                            .addLast(new ServerHandler());
                }
            });
            //启动
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            log.info("服务器启动");
            //管道关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }
    }


}
