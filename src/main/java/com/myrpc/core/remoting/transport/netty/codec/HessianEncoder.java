package com.myrpc.core.remoting.transport.netty.codec;

import com.caucho.hessian.io.Hessian2Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * @author: tangyang9464
 * @create: 2021-04-28 15:12
 **/
public class HessianEncoder extends MessageToByteEncoder<Serializable> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output h2O = new Hessian2Output(baos);
        h2O.writeObject(msg);
        h2O.flush();
        //写入数据长度，解决粘包
        out.writeInt(baos.size());
        //写入数据
        out.writeBytes(baos.toByteArray());
    }
}
