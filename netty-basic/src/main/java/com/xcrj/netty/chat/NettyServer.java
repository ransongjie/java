package com.xcrj.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) {
        /*
         * bossGroup
         * - 负责建立连接
         * - 含有NioEventLoop
         * workerGroup
         * - 负责读写和业务处理
         * - 含有NioEventLoop, 默认CPU核数*2
         */
        EventLoopGroup bossGroup=new NioEventLoopGroup(1);
        EventLoopGroup workerGroup=new NioEventLoopGroup();

        try{
            //服务端配置
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)//bossGroup channel
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//workerGroup channel
                    //.handler(null)//bossGroup handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {//workerGroup handler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("socketChannel hashcode=" + socketChannel.hashCode());
                            //handler加入pipeline中
                           ChannelPipeline channelPipeline = socketChannel.pipeline();
                           channelPipeline.addLast(new NettyServerChannelHandler());
                        }
                    });
            System.out.println("服务端配置准备完成...");

            //sync(): 当前线程等待异步线程bind()执行完成
            ChannelFuture channelFuture =serverBootstrap.bind(7000).sync();
            //添加监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("监听端口7000成功");
                    } else {
                        System.out.println("监听端口7000失败");
                    }
                }
            });

            //sync(): 当前线程等待异步线程closefuture()执行完成
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
