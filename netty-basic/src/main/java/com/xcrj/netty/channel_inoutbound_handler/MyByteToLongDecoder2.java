package com.xcrj.netty.channel_inoutbound_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecoder2  extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2被调用");
        //在ReplayingDecoder不需要判断数据是否足够读取，内部会进行处理
        out.add(in.readLong());
    }
}
