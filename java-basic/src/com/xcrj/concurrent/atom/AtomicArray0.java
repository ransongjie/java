package com.xcrj.concurrent.atom;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicArray
 */
public class AtomicArray0 {
    private static final int LEN = 10;
    private AtomicIntegerArray array = new AtomicIntegerArray(LEN);

    // 根据数组下标索引自增
    public void increment(int index) {
        array.getAndIncrement(index);
    }

    public int getValue(int index) {
        return array.get(index);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicArray0 atomicArray0 = new AtomicArray0();

        // 创建 10 个线程，每个线程执行 1000 次自增操作
        Thread[] threads = new Thread[LEN];
        for (int i = 0; i < threads.length; i++) {
            int index = i;
            threads[i] = new Thread(() -> {
                // 对对应线程自增1000次
                for (int j = 0; j < 1000; j++) {
                    atomicArray0.increment(index);
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出数组的最终值 每个索引位置都是1000的值
        for (int i = 0; i < LEN; i++) {
            System.out.println("arr[" + i + "]: " + atomicArray0.getValue(i));
        }
    }
}
