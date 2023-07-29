package com.xcrj.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count=0;

    /**
     * 使用此方法从客户端接收数据
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String message = new String(buffer, CharsetUtil.UTF_8);
        System.out.println("服务器接收到数据：" + message);
        System.out.println("服务器接收到数据数量：" + (++this.count));

        //服务器响应给客户端一个随机id
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(
                UUID.randomUUID().toString(),
                CharsetUtil.UTF_8);
        channelHandlerContext.writeAndFlush(responseByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
