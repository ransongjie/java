package com.xcrj.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * ChannelInboundHandlerAdapter, InboundChannel，入站
 */
public class NettyServerChannelHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道可读，执行此方法
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext,Object msg)throws Exception{
        /*
         * 耗时任务处理：异步处理，将耗时任务提交到任务队列中，仍然使用同一个线程处理
         * 将耗时任务提交到taskQueue/scheduleTaskQueue中，等待NioEventLoop线程处理
         */
        channelHandlerContext.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5*1000);
                    ByteBuf byteBuf= Unpooled.copiedBuffer("hello, 我是服务端，喵喵2", CharsetUtil.UTF_8);
                    channelHandlerContext.writeAndFlush(byteBuf);
                    System.out.println("channel hashcode="+channelHandlerContext.channel().hashCode());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        channelHandlerContext.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5*1000);
                    ByteBuf byteBuf= Unpooled.copiedBuffer("hello, 我是服务端，喵喵3", CharsetUtil.UTF_8);
                    channelHandlerContext.writeAndFlush(byteBuf);
                    System.out.println("channel hashcode="+channelHandlerContext.channel().hashCode());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        channelHandlerContext.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5*1000);
                    ByteBuf byteBuf= Unpooled.copiedBuffer("hello, 我是服务端，喵喵4", CharsetUtil.UTF_8);
                    channelHandlerContext.writeAndFlush(byteBuf);
                    System.out.println("channel hashcode="+channelHandlerContext.channel().hashCode());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },5, TimeUnit.SECONDS);

        /*
         * 非耗时任务，直接由此线程处理
         */
        System.out.println("服务端读取线程：" + Thread.currentThread().getName());
        System.out.println("channel hashcode="+channelHandlerContext.channel().hashCode());
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + channelHandlerContext.channel().remoteAddress());
    }

    /**
     * 通道读取完成，执行此方法
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        ByteBuf byteBuf= Unpooled.copiedBuffer("hello, 我是服务端，喵喵1", CharsetUtil.UTF_8);
        channelHandlerContext.writeAndFlush(byteBuf);
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
