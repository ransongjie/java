package com.xcrj.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 加入聊天通知
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        String msg="客户端：" + channel.remoteAddress() + "，加入聊天" + sdf.format(new Date()) + " \n";
        channelGroup.writeAndFlush(msg);
        channelGroup.add(channel);
    }

    /**
     * 离开聊天通知
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String msg="客户端：" + channel.remoteAddress() + "，离开聊天" + sdf.format(new Date()) + " \n";
        channelGroup.writeAndFlush(msg);
        System.out.println("channelGroup size=" + channelGroup.size());
    }

    /**
     * 上线通知
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端："+ctx.channel().remoteAddress() + "，上线了");
    }

    /**
     * 下线通知
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端："+ctx.channel().remoteAddress() + "，下线了");
    }

    /**
     * 转发消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel selfChannel = ctx.channel();
        System.out.println("客户端：" + selfChannel.remoteAddress() + "，发送了消息：" + msg);
        channelGroup.forEach(ch -> {
            if(selfChannel != ch) {
                ch.writeAndFlush("客户端：" + selfChannel.remoteAddress() + "，发送了消息：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
