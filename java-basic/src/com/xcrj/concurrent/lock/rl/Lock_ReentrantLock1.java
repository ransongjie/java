package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可中断锁
 * lockInterruptibly()
 */
public class Lock_ReentrantLock1 {
    private static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //线程在获取锁
        Thread thd=new Thread(()->{
            //没有获得锁
            try{
                System.out.println("thd 尝试获得锁");
                lock.lockInterruptibly();
            }catch(InterruptedException e){
                System.out.println("thd 没有获得锁，被中断");
                return;
            }
            
            //获得锁
            try{
                System.out.println("thd 获得锁");
            }finally{
                lock.unlock();
            }
        });

        //主线程先获取锁
        lock.lock();
        thd.start();
        //为了让thd启动
        Thread.currentThread().sleep(1000);
        System.out.println("主线程中断 thd 线程");
        thd.interrupt();
    }
}
