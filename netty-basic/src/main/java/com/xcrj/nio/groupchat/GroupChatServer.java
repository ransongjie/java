package com.xcrj.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer {
    private ServerSocketChannel serverSocketChannel;
    private static  final int PORT=7000;
    private Selector selector;

    public GroupChatServer(){
        try{
            this.serverSocketChannel=ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress=new InetSocketAddress(PORT);
            ServerSocket serverSocket=this.serverSocketChannel.socket();
            serverSocket.bind(inetSocketAddress);
            this.selector=Selector.open();
            this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listen(){
        System.out.println("监听线程："+Thread.currentThread().getName());
        try{
            while(true){
                //是否有事件发生, 事件未发生将阻塞3s，阻塞3s之后再去检查事件是否发生
                if(this.selector.select(3000)==0){
                    System.out.println("服务器等待3S，无连接");
                    continue;
                }

                Set<SelectionKey> selectionKeys=this.selector.selectedKeys();
                Iterator<SelectionKey> it=selectionKeys.iterator();
                while(it.hasNext()){
                    SelectionKey selectionKey=it.next();

                    //是否可连接事件
                    if(selectionKey.isAcceptable()){
                        SocketChannel socketChannel=this.serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(this.selector,SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress()+"上线");
                    }

                    //是否可读事件
                    if(selectionKey.isReadable()){
                        this.read(selectionKey);
                    }

                    it.remove();//!!!删除当前的selectionKey防止重复处理
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void read(SelectionKey selectionKey){
        SocketChannel socketChannel=null;
        try {
            socketChannel=(SocketChannel)selectionKey.channel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            int read=socketChannel.read(byteBuffer);
            if(read>0){
                String msg=new String(byteBuffer.array());
                System.out.println("来自客户端消息："+msg.trim());
                //向其它的客户端转发消息(去掉自己)
                sendInfoToOtherClients(msg, socketChannel);
            }
        }catch (IOException e){
            try{
                System.out.println(socketChannel.getRemoteAddress() + " 离线了..");
                selectionKey.channel();//取消注册
                socketChannel.close();//关闭连接
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel selfSocketChannel ) throws  IOException{
        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        for (SelectionKey selectionKey:this.selector.keys()){
            Channel channel=selectionKey.channel();
            if(!(channel instanceof SocketChannel)) continue;
            SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
            if(socketChannel==selfSocketChannel) continue;
            ByteBuffer byteBuffer=ByteBuffer.wrap(msg.getBytes());
            socketChannel.write(byteBuffer);
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupCharServer=new GroupChatServer();
        groupCharServer.listen();
    }
}
