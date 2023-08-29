package com.xcrj.sync;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 静态 非静态 方法的阻塞 非阻塞
 */
public class Test1 {

    public synchronized void test1() {
        System.out.println("普通方法：" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(3);//休眠3s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void test2() {
        System.out.println("静态方法：" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(3);//休眠3s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 线程池执行两次静态方法，阻塞，锁对象相同
        threadPool.execute(() -> {
            Test1.test2();
        });
        threadPool.execute(() -> {
            Test1.test2();
        });

        // 线程池执行两次普通方法，不阻塞，锁对象不同
        threadPool.execute(() -> {
            Test1 tst1 = new Test1();
            tst1.test1();
        });
        threadPool.execute(() -> {
            Test1 tst2 = new Test1();
            tst2.test1();
        });

        // 线程池执行两次普通方法，阻塞，锁对象相同
        Test1 tst3 = new Test1();
        threadPool.execute(() -> {
            tst3.test1();
        });
        threadPool.execute(() -> {
            tst3.test1();
        });

        threadPool.shutdown();
    }
}
