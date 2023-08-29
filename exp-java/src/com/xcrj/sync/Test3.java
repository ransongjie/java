package com.xcrj.sync;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test3 {

    public void test1() {
        synchronized (this) {
            System.out.println("this：" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test2() {
        synchronized (Test3.class) {
            System.out.println(".class：" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 线程池执行两次.class加锁代码块，阻塞，锁对象相同
        threadPool.execute(() -> {
            Test3 tst = new Test3();
            tst.test2();
        });
        threadPool.execute(() -> {
            Test3 tst = new Test3();
            tst.test2();
        });

        // 线程池执行两次this加锁代码块，不阻塞，锁对象不同
        threadPool.execute(() -> {
            Test3 tst = new Test3();
            tst.test1();
        });
        threadPool.execute(() -> {
            Test3 tst = new Test3();
            tst.test1();
        });

        // 线程池执行两次this加锁方法，阻塞，锁对象相同
        Test3 tst3 = new Test3();
        threadPool.execute(() -> {
            tst3.test1();
        });
        threadPool.execute(() -> {
            tst3.test1();
        });

        threadPool.shutdown();
    }
}
