package com.xcrj.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline=socketChannel.pipeline();
                            /**
                             * 1. IdleStateHandler是netty提供的处理空闲状态的处理器
                             * 2. long readerIdleTime: 表示多长时间没有读, 就会发送一个心跳检测包检测是否连接
                             * 3. long writerIdleTime: 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接
                             * 4. long allIdleTime: 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接
                             */
                            channelPipeline
                                    .addLast(new IdleStateHandler(7000,7000,10, TimeUnit.SECONDS))
                                    .addLast(new MyNettyServerHandler());
                        }
                    });
            System.out.println("服务端配置准备完成...");

            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
