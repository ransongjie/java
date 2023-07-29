package com.xcrj.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用buffer channel 读取文件
 */
public class FileChannel02 {
    public static void main(String[] args) throws Exception{
        File file=new File("result/file01.txt");
        FileInputStream fis=new FileInputStream(file);
        FileChannel fileChannel=fis.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate((int)file.length());
        //从channel读取到buffer
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fis.close();
    }
}
