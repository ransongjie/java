package com.xcrj.concurrent.lock.ls;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替输出
 * LockSupport.park(); LockSupport.unpark(nxtThd);
 * t1输出a，t2输出b，t3输出c，共输出5次
 * abc abc abc abc abc
 */
public class LockSupport7 {
    private static Thread t1;
    private static Thread t2;
    private static Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnpark=new ParkUnpark(5);

        t1=new Thread(()->{
            parkUnpark.hello(t2, "a");
        },"t1");
        t2=new Thread(()->{
            parkUnpark.hello(t3, "b");
        },"t2");
        t3=new Thread(()->{
            parkUnpark.hello(t1, "c");
        },"t3");
        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnpark {
    private int loopNum;
    ParkUnpark (int loopNum){
        this.loopNum=loopNum;
    }
    public void hello(Thread nxtThd,String msg){
        for (int i = 0; i < this.loopNum; i++) {
            LockSupport.park();
            System.out.println(msg);
            LockSupport.unpark(nxtThd);
        }
    }
}
