package com.xcrj.sync;

/**
 * 4个线程，2线程+，2线程-
 * 设计4个线程，其中2个线程每次对j增加1，另外2个线程对j每次减少1
 *
 * 加锁目的，一个时间段内只有1个线程操作共享变量，线程执行顺序
 */
public class ThreadTest1 {
    private int j;

    public static void main(String[] args) {
        ThreadTest1 tt = new ThreadTest1();
        Inc inc = tt.new Inc();
        Dec dec = tt.new Dec();

        /**
         * 1次循环，1个j++，1个j--
         */
        for (int i = 0; i < 2; i++) {
            new Thread(inc).start();
            new Thread(dec).start();
        }
    }

    /**
     * j+任务
     */
    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    /**
     * j-任务
     */
    class Dec implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }

    /**
     * j++
     * 对共享变量j进行操作，需要加锁
     */
    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    /**
     * j--
     * 对共享变量j进行操作，需要加锁
     */
    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }
}