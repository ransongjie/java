package com.xcrj.netty.rpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientChannelHandler nettyClientChannelHandler;
    private int count = 0;

    //代理模式
    public Object getBean(final Class<?> serivceClass, final String providerName) {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serivceClass},
                (proxy, method, args) -> {
                    System.out.println("proxy被调用：" + (++count) + " 次");
                    if (nettyClientChannelHandler == null) {
                        startClient();
                    }
                    //设置要发给服务器端的信息
                    nettyClientChannelHandler.setParam(providerName + args[0]);
                    return executorService.submit(nettyClientChannelHandler).get();
                });
    }

    private static final String HOST="localhost";
    private static final int PORT=7000;
    private static void startClient() {
        nettyClientChannelHandler = new NettyClientChannelHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    ChannelPipeline pipeline = socketChannel.pipeline();
                                    pipeline.addLast(new StringDecoder())
                                            .addLast(new StringEncoder())
                                            .addLast(nettyClientChannelHandler);
                                }
                            }
                    );
            System.out.println("客户端配置准备完成...");

            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();
//            channelFuture.channel().closeFuture().sync();//外部调用此方法，将阻塞调用线程，无法进行后续操作
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            group.shutdownGracefully();//外部调用此方法，方法return时会关闭group
        }
    }
}