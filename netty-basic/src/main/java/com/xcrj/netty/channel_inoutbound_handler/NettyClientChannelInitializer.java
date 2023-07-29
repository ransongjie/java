package com.xcrj.netty.channel_inoutbound_handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyClientChannelInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站的handler 对数据进行一个编码
        pipeline
                .addLast(new MyLongToByteEncoder())//outbound, 编码
                .addLast(new MyByteToLongDecoder())//inbound, 解码
                //.addLast(new MyByteToLongDecoder2())//inbound, 解码
                .addLast(new NettyClientChannelHandler());//inbound, 解码
    }
}
