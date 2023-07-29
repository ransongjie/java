package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时锁
 * tryLock(1,TimeUnit.SECONDS)
 */
public class Lock_ReentrantLock3 {
    private static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException{
        //线程再获得锁
        Thread thd=new Thread(()->{
            System.out.println("尝试获得锁");
            try{
                if(!lock.tryLock(1,TimeUnit.SECONDS)){
                    System.out.println("1S内，尝试获取锁失败");
                    return;
                }
            }catch(InterruptedException e){
                System.out.println("thd 获取不到锁");
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
