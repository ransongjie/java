package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()
 * 定时锁
 */
public class Lock_ReentrantLock2 {
    private static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException{
        //线程再获得锁
        Thread thd=new Thread(()->{
            System.out.println("尝试获得锁");
            if(!lock.tryLock()){
                System.out.println("尝试获取锁失败");
                return;
            }

            try{
                System.out.println("尝试获得锁");
            }finally{
                lock.unlock();
            }
        },"thd");

        //主线程先获得锁
        lock.lock();
        System.out.println("主线程获得锁");
        thd.start();
        Thread.currentThread().sleep(2000);
        System.out.println("主线程释放锁");
        lock.unlock();
    }
}
