package com.xcrj.concurrent.lock.ls;

import java.util.concurrent.locks.LockSupport;

/**
 * 同步
 * LockSupport.park(); LockSupport.unpark(t2);
 * t1先输出1, t2再输出2
 * t2等t1输出1之后再执行输出2
 */
public class LockSupport6 {
    public static void main(String[] args) {
        Thread t2=new Thread(()->{
            LockSupport.park();
            System.out.println("t2 2");
        },"t2");

        Thread t1=new Thread(()->{
            System.out.println("t1 1");
            LockSupport.unpark(t2);
        },"t1");

        t1.start();
        t2.start();
    }
}
