package com.xcrj.netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder  extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder被调用");

        //解码为MyMsg对象
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        MyMessage myMessage = new MyMessage();
        myMessage.setLen(length);
        myMessage.setContent(content);
        //out非空，传递给下一个handler处理
        out.add(myMessage);
    }
}
