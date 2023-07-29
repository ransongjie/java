package com.xcrj.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        //创建
        ByteBuffer buffer=ByteBuffer.allocate(64);
        //写入
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        //限制只读
        ByteBuffer readOnlyBuffer=buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        readOnlyBuffer.flip();
        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        //测试写入
        readOnlyBuffer.put((byte)100);//ReadOnlyBufferException
    }
}
