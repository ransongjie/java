package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步
 * lock.lock(); lock.unlock(); c.await(); c.signal(); 
 * t1先输出1, t2再输出2
 * t2等t1输出1之后再执行输出2
 */
public class Lock_ReentrantLock6 {
    private static final ReentrantLock lock=new ReentrantLock();
    private static final Condition c=lock.newCondition();
    private static boolean t1Runned=false;
    
    public static void main(String[] args) {
        //
        Thread t1=new Thread(()->{
            lock.lock();
            try{
                System.out.println("t1 1");
                t1Runned=true;
                c.signal();
            }finally{
                lock.unlock();
            }
        },"t1");

        //
        Thread t2=new Thread(()->{
            lock.lock();
            try{
                while(!t1Runned){
                    c.await();
                }
                System.out.println("t2 2");
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
