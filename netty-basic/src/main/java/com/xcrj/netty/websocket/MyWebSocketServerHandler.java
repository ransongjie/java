package com.xcrj.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 收到消息，执行此方法
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        System.out.println("channelRead0被调用");
        System.out.println("服务器收到消息："+msg.text());
        //回复消息
        TextWebSocketFrame reply=new TextWebSocketFrame("你好，客户端。现在的服务器时间是："+LocalDateTime.now());
        channelHandlerContext.channel().writeAndFlush(reply);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        //channel的全局唯一id，LongText，ShortText截取的LongText最后一段
        System.out.println("handlerAdded被调用" + channelHandlerContext.channel().id().asLongText());
        System.out.println("handlerAdded被调用" + channelHandlerContext.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("handlerRemoved被调用" + channelHandlerContext.channel().id().asLongText());
    }

    /**
     * 处理异常, 一般是需要关闭通道
     * @param channelHandlerContext
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
