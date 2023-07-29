package com.xcrj.netty.tcp2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageEncoder());//outbound
        pipeline.addLast(new MyMessageDecoder());//inbound
        pipeline.addLast(new NettyClientChannelHandler());//inbound
    }
}
