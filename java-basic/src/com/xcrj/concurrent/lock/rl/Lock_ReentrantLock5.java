package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件队列
 * condition.await() condition.signal()
 * 流程
 * - 获得锁
 * - 调用await()，释放锁
 * - 进入条件队列
 * - 被唤醒/中断/超时，重新竞争锁
 * - 获得锁，继续执行后序步骤
 * 
 * condition.signal()
 * - 选择waitCigrette条件队列一个线程唤醒
 */
public class Lock_ReentrantLock5 {
    private static ReentrantLock lock = new ReentrantLock();
    // 有没有厕纸
    private static boolean hasTissue = false;
    // 有没有香烟
    private static boolean hasCigrette = false;
    // 等待香烟队列
    private static Condition waitCigrette = lock.newCondition();
    // 等待厕纸队列
    private static Condition waitTissue = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        // xcrj线程 等待香烟
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("有没有香烟？" + hasCigrette);
                while (!hasCigrette) {
                    System.out.println("没有香烟，去等待香烟队列");
                    try {
                        waitCigrette.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("拿到香烟咯");
            } finally {
                lock.unlock();
            }
        }, "xcrj").start();

        // xcrj1线程 等待厕纸
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("有没有厕纸？" + hasCigrette);
                while (!hasTissue) {
                    System.out.println("没有厕纸，去等待厕纸队列");
                    try {
                        waitTissue.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("拿到厕纸咯");
            } finally {
                lock.unlock();
            }
        }, "xcrj1").start();

        // xcrj2线程 送香烟
        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("香烟送到");
                hasCigrette = true;// 修改循环标志
                waitCigrette.signal();// 选择waitCigrette条件队列一个线程唤醒
            } finally {
                lock.unlock();
            }
        }, "xcrj2").start();

        // xcrj3线程 送厕纸
        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("厕纸送到");
                hasTissue = true;// 修改循环标志
                waitTissue.signal();// 选择waitCigrette条件队列一个线程唤醒
            } finally {
                lock.unlock();
            }
        }, "xcrj3").start();
    }
}
