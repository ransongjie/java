package com.xcrj.netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MyMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder方法被调用");
        //MyMsg对象编码到ByteBuf out中
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
