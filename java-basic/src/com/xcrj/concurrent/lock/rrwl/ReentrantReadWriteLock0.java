package com.xcrj.concurrent.lock.rrwl;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantReadWriteLock0 {
    public static void main(String[] args) {
        testReentrantLock();
        // testReentrantReadWriteLock();
    }

    /**
     * 20s执行完成
     */
    public static void testReentrantLock(){
        TestReentrantLock testReentrantLock=new TestReentrantLock();
        //读操作，读多
        for (int i = 0; i < 18; i++) {
            new Thread(()->{testReentrantLock.read();}).start();
        }
        //写操作，写少
        for (int i = 0; i < 2; i++) {
            new Thread(()->{testReentrantLock.write(1);}).start();
        }
    }

    /**
     * 读锁上可以再加读锁
     * 1s内读完
     * 2s内写完
     */
    public static void testReentrantReadWriteLock(){
        TestReentrantReadWriteLock tReadWriteLock=new TestReentrantReadWriteLock();
        //读操作，读多
        for (int i = 0; i < 18; i++) {
            new Thread(()->{tReadWriteLock.read();}).start();
        }
        //写操作，写少
        for (int i = 0; i < 2; i++) {
            new Thread(()->{tReadWriteLock.write(1);}).start();
        }
    }
}

/**
 * ReentrantLock一把锁
 */
class TestReentrantLock {
    static int value;
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * 读操作
     * 读加读锁
     * @param lock
     */
    public static void read() {
        lock.lock();
        try {
            System.out.println("开始读取value="+value);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 写操作
     * 写加写锁
     * @param lock
     * @param v
     */
    public static void write(int v) {
        lock.lock();
        try {
            System.out.println("开始写value");
            Thread.sleep(1000);
            value = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }    
}

/**
 * ReentrantReadWriteLock一把读锁, 一把写锁
 */
class TestReentrantReadWriteLock{
    static int value;
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static WriteLock writeLock=lock.writeLock();
    private static ReadLock readLock=lock.readLock();
    /**
     * 读操作
     * @param lock
     */
    public static void read() {
        readLock.lock();
        try {
            System.out.println("开始读取value="+value);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 写操作
     * @param lock
     * @param v
     */
    public static void write(int v) {
        writeLock.lock();
        try {
            System.out.println("开始写value");
            Thread.sleep(1000);
            value = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }    
}