package com.xcrj.netty.tcp2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private static final String HOST="localhost";
    private static final int PORT=7000;

    public static void main(String[] args)  throws  Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientChannelInitializer());
            System.out.println("客户端配置准备完成...");

            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
