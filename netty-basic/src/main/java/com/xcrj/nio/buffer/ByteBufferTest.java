package com.xcrj.nio.buffer;

import java.nio.ByteBuffer;

/**
 * ByteBuffer常用方法
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        //创建
        ByteBuffer byteBuffer=ByteBuffer.allocate(64);
        //写入
        byteBuffer.putInt(100);
        byteBuffer.putShort((short)20);
        byteBuffer.putLong(9L);
        byteBuffer.putChar('我');
        //读取
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
    }
}
