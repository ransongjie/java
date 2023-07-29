package com.xcrj.netty.channel_inoutbound_handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * ChannelInboundHandler, ChannelOutboundHandler是两条链路
 */
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyByteToLongDecoder())//inbound，解码handler
                //.addLast(new MyByteToLongDecoder2())//inbound，解码handler
                .addLast(new MyLongToByteEncoder())//outbound，编码handler
                .addLast(new NettyServerChannelHandler());//inbound，处理业务
    }
}
