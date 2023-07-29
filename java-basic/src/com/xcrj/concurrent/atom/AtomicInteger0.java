package com.xcrj.concurrent.atom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 */
public class AtomicInteger0 {
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public void increment(){
        atomicInteger.incrementAndGet();
    }
    public int getValue(){
        return atomicInteger.get();
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger0 atomicInteger0=new AtomicInteger0();
        //创建 10 个线程，每个线程执行 1000 次自增操作
        Thread[] thds=new Thread[10];
        for (int i = 0; i < 10; i++) {
            thds[i]=new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    atomicInteger0.increment();
                }
            });
            thds[i].start();
        }
        //主线程等待所有线程执行完毕
        for (Thread thread : thds) {
            thread.join();
        }
        //获取最终的结果 10,000
        System.out.println(atomicInteger0.getValue());
    }
}
