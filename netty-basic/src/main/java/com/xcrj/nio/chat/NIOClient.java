package com.xcrj.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 7000);
        //非阻塞连接
        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("连接需要时间，客户端不会阻塞，可以做其它工作");
                Thread.sleep(1000);
            }
        }

        String str="hello ransongjie";
        ByteBuffer byteBuffer=ByteBuffer.wrap(str.getBytes());
        //byteBuffer>>channel
        socketChannel.write(byteBuffer);
        System.in.read();//测试只用
    }
}
