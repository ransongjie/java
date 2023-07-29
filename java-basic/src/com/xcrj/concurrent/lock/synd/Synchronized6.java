package com.xcrj.concurrent.lock.synd;

/**
 * 同步
 * synchronized (obj) obj.wait(); obj.notify();
 * t1先输出1, t2再输出2
 * t2等t1输出1之后再执行输出2
 */
public class Synchronized6 {
    private static final Object obj = new Object();
    private static boolean t1Runned = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("t1 1");
                t1Runned = true;
                obj.notify();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    while (!t1Runned) {
                        obj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 2");
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
