package com.xcrj.nio.buffer;

import java.nio.IntBuffer;

/**
 * 简单说明Buffer的使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //创建一个Buffer,大小为 5,即可以存放5个int
        IntBuffer intBuffer=IntBuffer.allocate(5);
        //放入数据到buffer
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }

        //从buffer中读取数据
        intBuffer.flip();
        /*
         * flip()说明
         * public final Buffer flip() {
         *     limit = position;
         *     position = 0;
         *     mark = -1;
         *     return this;
         * }
         */
        intBuffer.position(1);//起点
        System.out.println(intBuffer.get());//获取，position++
        intBuffer.limit(3);//终点, position<limit
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
