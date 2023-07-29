package com.xcrj.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<StudentPOJO.Student>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student msg) throws Exception {
        System.out.println("客户端发送的数据 id=" + msg.getId() + " 名字=" + msg.getName());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好，客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
