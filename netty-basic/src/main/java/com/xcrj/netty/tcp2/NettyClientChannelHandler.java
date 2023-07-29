package com.xcrj.netty.tcp2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyClientChannelHandler extends SimpleChannelInboundHandler<MyMessage> {
    /**
     * 客户端向服务端发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive被调用");
        for(int i = 0; i< 1; i++) {
            String mes = "你好服务端";
            byte[] content = mes.getBytes(CharsetUtil.UTF_8);
            int length = mes.getBytes(CharsetUtil.UTF_8).length;
            MyMessage myMessage = new MyMessage();
            myMessage.setLen(length);
            myMessage.setContent(content);
            ctx.writeAndFlush(myMessage);
        }
    }

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        System.out.println("channelRead0被调用");
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端接收到消息如下：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));
        System.out.println("客户端接收消息数量：" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
