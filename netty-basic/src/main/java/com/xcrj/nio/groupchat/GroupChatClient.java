package com.xcrj.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {
    private final SocketChannel socketChannel;
    private final Selector selector;
    private static final String HOST="localhost";
    private static final int PORT=7000;
    private final String username;

    public GroupChatClient() throws IOException {
        InetSocketAddress inetSocketAddress=new InetSocketAddress(HOST,PORT);
        this.socketChannel=SocketChannel.open(inetSocketAddress);
        this.socketChannel.configureBlocking(false);
        this.selector=Selector.open();
        this.socketChannel.register(this.selector, SelectionKey.OP_READ);

        this.username=this.socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(this.username+ " is ok...");
    }

    public void send(String msg){
        msg=this.username+"说："+msg;
        try {
            ByteBuffer byteBuffer=ByteBuffer.wrap(msg.getBytes());
            this.socketChannel.write(byteBuffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void read(){
        try {
            //是否有事件发生
//            if(this.selector.select(1000)==0){//报错
            if(this.selector.select()==0){
                System.out.println("没有可以用的通道...");
                return;
            }
            Set<SelectionKey> selectionKeys=this.selector.selectedKeys();
            Iterator<SelectionKey> it=selectionKeys.iterator();
            while (it.hasNext()){
                SelectionKey selectionKey=it.next();
                //是否可读事件
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    String msg=new String(byteBuffer.array());
                    System.out.println(msg.trim());
                }
                it.remove();//删除当前的selectionKey, 防止重复操作
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        GroupChatClient groupChatClient=new GroupChatClient();

        //单独开启一个线程read
        new Thread(()->{
                while(true) {
                    groupChatClient.read();
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();

        //使用主线程send
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            groupChatClient.send(msg);
        }
    }
}
