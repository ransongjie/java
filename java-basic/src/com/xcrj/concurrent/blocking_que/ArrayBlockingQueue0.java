package com.xcrj.concurrent.blocking_que;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 线程安全队列
 * ArrayBlockingQueue
 */
public class ArrayBlockingQueue0 {
    public static void main(String[] args) throws InterruptedException {
        //构造函数还有公平阻塞队列
        //注意，容量是固定的
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1000);

        // 创建 10 个线程，每个线程向队列中添加 100 个元素。共1000个元素
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    int value = (int)(Math.random()*1000);
                    try {
                        // put
                        queue.put(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出队列中的元素个数和内容 1000
        System.out.println("Queue size: " + queue.size());
        // while (!queue.isEmpty()) {
        // // take
        // System.out.println(queue.take());
        // }
    }
}
