package com.xcrj.spin_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * todo(加锁次数问题)
 */

public class Main1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable worker = new Worker();

        for (int i = 0; i < 2; i++) {
            executor.submit(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}
    }
}

class Worker implements Runnable {
    private SpinLock2 slock = new SpinLock2();

    public void run() {
        slock.lock();
        slock.lock(); // 按以上实现，这一句什么也不做
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s,", Thread.currentThread().getName());
        }
        System.out.println("");
        slock.unlock();
        slock.unlock(); // 按以上实现，若解锁成功，这一句什么也不做
    }
}

/**
 * 自旋锁
 */
class SpinLock {
    // 原子性
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        // 加自旋锁，expected!=null则自旋
        // param1=expected=null, param2=next=current
        while (!sign.compareAndSet(null, current)) {
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // 解锁时sign的值一定为current，所以直接把sign设置为null
        sign.compareAndSet(current, null);
    }
}

/**
 * 可重入自旋锁
 */
class SpinLock2 extends ReentrantLock {
    public SpinLock2() {
        super();
    }

    @Override
    public void lock() {
        // 可重入，已经获取锁则，不自旋
        while (!super.tryLock()) {
        }
    }

    @Override
    public void unlock() {
        super.unlock();
    }
}

/**
 * 可重入自旋锁
 */
class SpinLock3 {
    // 原子性
    private AtomicReference<Thread> sign = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        // 可重入，已经获取锁，return
        if (current == sign.get()) {
            return;
        }
        while (!sign.compareAndSet(null, current)) {
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == sign.get()) {
            sign.compareAndSet(current, null);
        }
    }
}
