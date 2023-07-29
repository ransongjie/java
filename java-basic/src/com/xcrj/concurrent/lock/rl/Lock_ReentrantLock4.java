package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * new ReentrantLock(true)
 */
public class Lock_ReentrantLock4 {
    private static ReentrantLock lock = new ReentrantLock(true);

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
