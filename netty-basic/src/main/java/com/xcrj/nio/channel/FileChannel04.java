package com.xcrj.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 * destChannel.transferFrom(sourceChannel,0,sourceChannel.size());
 */
public class FileChannel04 {
    public static void main(String[] args) throws Exception{
        FileInputStream fis=new FileInputStream("result/1.txt");
        FileOutputStream fos=new FileOutputStream("result/3.txt");
        FileChannel fisFileChannel=fis.getChannel();
        FileChannel fosFileChannel=fos.getChannel();
        //
        fosFileChannel.transferFrom(fisFileChannel,0,fisFileChannel.size());
        fos.close();
        fis.close();
    }
}
