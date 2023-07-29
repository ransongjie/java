package com.xcrj.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪，执行此方法
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        ByteBuf byteBuf=Unpooled.copiedBuffer("hello, 我是客户端", CharsetUtil.UTF_8);
        channelHandlerContext.writeAndFlush(byteBuf);
    }

    /**
     * 通道可读，执行此方法
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址："+ channelHandlerContext.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
