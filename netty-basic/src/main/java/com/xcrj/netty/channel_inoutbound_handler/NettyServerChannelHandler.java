package com.xcrj.netty.channel_inoutbound_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从客户端" + ctx.channel().remoteAddress() + "，读取到long：" + msg);
        //给客户端发送一个long
        ctx.writeAndFlush(98765L);
    }
}
