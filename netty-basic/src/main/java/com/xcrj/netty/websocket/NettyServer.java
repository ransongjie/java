package com.xcrj.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup=new NioEventLoopGroup(1);
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline=socketChannel.pipeline();
                            /*
                             * 里面的channelHandler全是ChannelInboundHandler
                             * ChunkedWriteHandler: 以块方式写
                             * HttpObjectAggregator: http client分段传输，server聚合
                             * WebSocketServerProtocolHandler: 升级http为ws,，保持长连接
                             * NettyServerHandler: 自定义的handler，处理业务逻辑
                             */
                            channelPipeline
                                    .addLast(new HttpServerCodec())
                                    .addLast(new ChunkedWriteHandler())
                                    .addLast(new HttpObjectAggregator(8192))
                                    .addLast(new WebSocketServerProtocolHandler("/hello"))
                                    .addLast(new MyWebSocketServerHandler());
                        }
                    });
            System.out.println("服务端配置准备完成...");

            //sync(): 当前线程等待异步线程bind()执行完成
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
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
