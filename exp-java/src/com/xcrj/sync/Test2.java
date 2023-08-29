package com.xcrj.sync;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 代码块的阻塞 非阻塞
 */
public class Test2 {
    private Object comObj = new Object();
    private static Object stcObj = new Object();

    public void test1() {
        synchronized (comObj) {
            System.out.println("普通成员对象：" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test2() {
        synchronized (stcObj) {
            System.out.println("静态成员对象：" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 线程池执行两次静态对象加锁代码块，阻塞，锁对象相同
        threadPool.execute(() -> {
            Test2 tst = new Test2();
            tst.test2();
        });
        threadPool.execute(() -> {
            Test2 tst = new Test2();
            tst.test2();
        });

        // 线程池执行两次普通对象加锁代码块，不阻塞，锁对象不同
        threadPool.execute(() -> {
            Test2 tst = new Test2();
            tst.test1();
        });
        threadPool.execute(() -> {
            Test2 tst = new Test2();
            tst.test1();
        });

        // 线程池执行两次普通对象加锁方法，阻塞，锁对象相同
        Test2 tst3 = new Test2();
        threadPool.execute(() -> {
            tst3.test1();
        });
        threadPool.execute(() -> {
            tst3.test1();
        });

        threadPool.shutdown();
    }
}
