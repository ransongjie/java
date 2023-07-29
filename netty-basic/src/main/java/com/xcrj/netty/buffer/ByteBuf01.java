package com.xcrj.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBuf01 {
    public static void main(String[] args) {
        /*
         * ByteBuf不需要java nio的ByteBuffer的flip()
         * readerIndex>可读区域>writerIndex>可写区域>capacity
         * ByteBuf可以扩展，向ByteBuf写数据的时候，如果容量不足，可以进行扩容，直到maxCapacity
         */
        ByteBuf byteBuf =Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);//
        }
        System.out.println("capacity="+byteBuf.capacity());
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());//
        }
    }
}
