package com.xcrj.netty.tcp2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<MyMessage> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        //服务端收到的消息
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到信息如下：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务端接收到消息包数量：" + (++count));

        //服务端回复消息给客户端
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes(CharsetUtil.UTF_8).length;
        byte[]  responseContent2 = responseContent.getBytes(CharsetUtil.UTF_8);
        MyMessage myMessage = new MyMessage();
        myMessage.setLen(responseLen);
        myMessage.setContent(responseContent2);
        ctx.writeAndFlush(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
