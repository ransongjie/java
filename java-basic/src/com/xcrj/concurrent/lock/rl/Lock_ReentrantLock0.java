package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * lock()
 * 同一个线程首次获得这把锁，可以再次获得这把锁
 */
public class Lock_ReentrantLock0 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("main");
            test1();
        } finally {
            lock.unlock();
        }
    }


    public static void test1() {
        lock.lock();
        try {
            System.out.println("test1");
            test2();
        } finally {
            lock.unlock();
        }
    }

    public static void test2() {
        System.out.println("test2");
    }
}
