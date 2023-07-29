package com.xcrj.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
//        serverSocketChannel.configureBlocking(false);//这里使用阻塞式
        InetSocketAddress inetSocketAddress=new InetSocketAddress(7001);
        ServerSocket socket=serverSocketChannel.socket();
        socket.bind(inetSocketAddress);

        ByteBuffer byteBuffer=ByteBuffer.allocate(4096);
        while (true){
            //线程阻塞在这里，因为上面为配置非阻塞模式
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount=0;
            while (-1!=readCount){
                try{
                    readCount=socketChannel.read(byteBuffer);
                }catch (Exception e){
                    break;
                }
                //倒带
                byteBuffer.rewind();
                /*
                 * rewind()说明
                 * public Buffer rewind() {
                 *     position = 0;
                 *     mark = -1;
                 *     return this;
                 * }
                 */
            }
        }

    }
}
