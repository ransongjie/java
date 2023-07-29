package com.xcrj.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf= Unpooled.copiedBuffer("hello, world!", Charset.forName("utf-8"));
        if(byteBuf.hasArray()){
            byte[]content=byteBuf.array();
            System.out.println(new String(content,Charset.forName("utf-8")));

            System.out.println(byteBuf.arrayOffset());//0
            System.out.println(byteBuf.readerIndex());//0
            System.out.println(byteBuf.writerIndex());//13
            System.out.println(byteBuf.capacity());//39
            System.out.println(byteBuf.readableBytes());//13
            System.out.println(byteBuf.getByte(0));//h=104
            System.out.println(byteBuf.getCharSequence(0,5,Charset.forName("utf-8")));//hello
        }
    }
}
