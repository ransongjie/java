package com.xcrj.nio.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用buffer channel 写入文件
 */
public class FileChannel01 {
    public static void main(String[] args) throws Exception{
        FileOutputStream fos=new FileOutputStream("result/file01.txt");
        //channel
        FileChannel fileChannel=fos.getChannel();
        //buffer
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        String str="hello, ransongjie";
        byte[] bytes=str.getBytes();
        //放入
        byteBuffer.put(bytes);
        //读取到channel中
        byteBuffer.flip();//!!!
        fileChannel.write(byteBuffer);
        fos.close();
    }
}
