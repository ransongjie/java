package com.xcrj.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 0拷贝，不用在内核/用户缓冲区内外，拷贝来拷贝去
 * 内核缓冲区没有数据是重复的，只有kernel buffer一份数据
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
//        socketChannel.configureBlocking(false);//这里使用阻塞式
        InetSocketAddress inetSocketAddress=new InetSocketAddress("localhost",7001);
        socketChannel.connect(inetSocketAddress);
        String filename="result/protoc-3.6.1-win32.zip";
        FileInputStream fis=new FileInputStream(filename);
        FileChannel fisFileChannel=fis.getChannel();

        long startTime = System.currentTimeMillis();

        /*
         * fisFileChannel>>socketChannel
         * transferTo 底层使用零拷贝
         * 在linux下一个transferTo就可以完成传输
         * 在windows下一次调用transferTo只能发送8M, 就需要分段传输文件
         */
        long transferCount = fisFileChannel.transferTo(0, fisFileChannel.size(), socketChannel);

        long endTime=System.currentTimeMillis();
        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (endTime- startTime));
        fisFileChannel.close();
    }
}
