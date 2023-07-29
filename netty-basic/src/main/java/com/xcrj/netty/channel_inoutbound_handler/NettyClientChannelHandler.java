package com.xcrj.netty.channel_inoutbound_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientChannelHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务端ip：" + ctx.channel().remoteAddress());
        System.out.println("收到服务端消息：" + msg);
    }

    /**
     * 给服务端发送一个long
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(123456L);
    }
}
