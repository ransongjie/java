package com.xcrj.nio.chat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Buffer Channel Selector SelectionKey
 *
 * Selector监听各种SelectionKey(事件)，发生对应事件执行对应操作
 * 发生可连接事件，建立连接，注册可读事件
 * 发生可读事件，读取数据
 *
 * 对比BIO
 * 连接不阻塞
 * 读取数据不阻塞
 */
public class NIOServer {
    public static void main(String[] args) throws Exception{
        //1. ServerSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//设置为非阻塞
        InetSocketAddress inetSocketAddress=new InetSocketAddress(7000);
        ServerSocket serverSocket=serverSocketChannel.socket();
        serverSocket.bind(inetSocketAddress);
        //2. Selector
        Selector selector=Selector.open();
        //注册事件
        //把 serverSocketChannel 注册到 selector, select 事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册的selectionkey数量="+selector.keys().size());

        while (true){
            if(selector.select(1000)==0){
                System.out.println("服务器等待1S，无连接");
                continue;
            }

            //3. SelectionKey
            //获取事件
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            System.out.println("selectionkey数量="+selectionKeys.size());

            Iterator<SelectionKey> it=selectionKeys.iterator();
            while (it.hasNext()){
                SelectionKey selectionKey=it.next();
                //是否可连接事件
                if(selectionKey.isAcceptable()){
                    //4. SocketChannel
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel=" + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);//设置为非阻塞
                    //5. ByteBuffer
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    //一个selector监听多个事件
                    socketChannel.register(selector,SelectionKey.OP_READ,byteBuffer);
                    System.out.println("客户端连接后，注册的selectionkey数量="+selector.keys().size());
                }

                //是否可读事件
                if(selectionKey.isReadable()){
                    //获取selectionkey关联的socketChannel
                    SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
                    //获取selectionkey关联的byteBuffer
                    ByteBuffer byteBuffer=(ByteBuffer)selectionKey.attachment();
                    socketChannel.read(byteBuffer);//socketChannel>>byteBuffer
                    System.out.println("来自客户端的消息："+new String(byteBuffer.array()));
                }
            }
        }
    }
}
