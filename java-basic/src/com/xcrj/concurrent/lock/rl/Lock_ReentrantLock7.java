package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替输出
 * Condition c1 c2 c3; lock(); curc.await(); nxtc.signal(); unlock();
 * t1输出a，t2输出b，t3输出c，共输出5次
 * abc abc abc abc abc
 */
public class Lock_ReentrantLock7 {
    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition c1 = awaitSignal.newCondition();
        Condition c2 = awaitSignal.newCondition();
        Condition c3 = awaitSignal.newCondition();

        Thread t1 = new Thread(() -> {
            awaitSignal.hello(c1, c2, "a");
        });
        Thread t2 = new Thread(() -> {
            awaitSignal.hello(c2, c3, "b");
        });
        Thread t3 = new Thread(() -> {
            awaitSignal.hello(c3, c1, "c");
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println("开始");
        awaitSignal.lock();
        try {
            // 唤醒t1线程
            // signal必须在lock和unlock之间
            c1.signal();
            System.out.println();
        } finally {
            awaitSignal.unlock();
        }
        // 主线程unlock()之后，c1, c2 ,c3才能执行
    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNum;

    AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }

    /**
     * 三个线程放到三个等待队列中
     * 
     * @param curc
     * @param nxtc
     * @param msg
     */
    public void hello(Condition curc, Condition nxtc, String msg) {
        for (int i = 0; i < this.loopNum; i++) {
            super.lock();
            try {
                // await 必须在lock和unlock之间
                curc.await();
                System.out.println(msg);
                nxtc.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                super.unlock();
            }
        }
    }
}