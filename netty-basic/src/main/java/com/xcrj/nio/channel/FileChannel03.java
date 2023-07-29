package com.xcrj.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 * 使用buffer channel 读取文件 写入文件
 */
public class FileChannel03 {
    public static void main(String[] args) throws Exception{
        FileInputStream fis=new FileInputStream("result/1.txt");
        FileChannel fisFileChannel=fis.getChannel();
        FileOutputStream fos=new FileOutputStream("result/2.txt");
        FileChannel fosFileChannel=fos.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        while(true){
            byteBuffer.clear();
            /**
             * clear()说明
             * public final Buffer clear() {
             *     position = 0;
             *     limit = capacity;
             *     mark = -1;
             *     return this;
             * }
             */
            int read=fisFileChannel.read(byteBuffer);
            if(read==-1) break;
            byteBuffer.flip();
            fosFileChannel.write(byteBuffer);
        }

        fos.close();
        fis.close();
    }
}
