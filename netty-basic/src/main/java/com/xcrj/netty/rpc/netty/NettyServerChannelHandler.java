package com.xcrj.netty.rpc.netty;

import com.xcrj.netty.rpc.customer.ClientBootstrap;
import com.xcrj.netty.rpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerChannelHandler extends ChannelInboundHandlerAdapter {
    /**
     * 获取customer发送的消息，调用provider提供的服务
     * customer>protocol>provider
     * protocol这里简单要求，必须以"HelloService#hello#你好"开头
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg：" + msg);
        //校验自定义protocol
        if(msg.toString().startsWith(ClientBootstrap.providerName)) {
            String result = new HelloServiceImpl()
                    .hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
