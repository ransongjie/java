package com.xcrj.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientChannelHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext channelHandlerContext;
    private String result; //返回的结果
    private String param; //客户端调用方法时，传入的参数

    /**
     * 1. ctx.writeAndFlush(): 代理对象调用call()，发送数据给服务端(Provider)
     * 2. wait(): 等待服务端(Provider)响应的结果后，唤醒客户端(Customer)
     * 3. 返回服务端(Provider)响应的结果
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        channelHandlerContext.writeAndFlush(param);
        wait(); //等待channelRead()获取到服务器的结果后，唤醒
        return result; //服务方返回的结果
    }

    void setParam(String param) {
        this.param  = param;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify(); //服务端(Provider)响应结果后，唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
