package com.myrpc.serialize;

import com.caucho.hessian.io.Hessian2Input;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author: tangyang9464
 * @create: 2021-04-28 18:52
 **/
public class HessianDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //读取数据长度
        int len = in.readInt();
        byte[] dst = new byte[len];
        in.readBytes(dst,0,len);
        ByteArrayInputStream bais = new ByteArrayInputStream(dst);
        Hessian2Input h2i = new Hessian2Input(bais);
        out.add(h2i.readObject());
    }
}
