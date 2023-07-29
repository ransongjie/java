package com.xcrj.nio.buffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 一种直接字节缓冲区，其内容是文件的内存映射区域。
 * 直接操作文件所在内存。不需要 文件从内核内存空间 copy JVM内存空间
 * 注意：idea中可能看不到修改的结果，重新打开文件即可
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("result/1.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'x');
        mappedByteBuffer.put(1,(byte)'c');
        mappedByteBuffer.put(2,(byte)'r');
        mappedByteBuffer.put(3,(byte)'j');
//        mappedByteBuffer.put(5,(byte)10);//IndexOutOfBoundsException

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
