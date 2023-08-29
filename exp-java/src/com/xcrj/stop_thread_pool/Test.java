package com.xcrj.stop_thread_pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * threadPool.shutdown();
     * - 执行中的任务，执行完毕
     * - 再提交的任务，抛出异常 RejectedExecutionException
     */
    private static void test1() {
        // 10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 1000个任务
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 关闭线程池，再提交任务
        threadPool.shutdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池之后，再执行任务
        threadPool.execute(() -> {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * threadPool.isShutdown() 线程池是否调用了shutdown()
     * - true: 是调用shutdown()
     * - false: 否调用shutdown()
     */
    private static void test2() {
        // 10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 1000个任务
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 调用 shutdown() 方法之前
        System.out.println("threadPool.isShutdown()：" + threadPool.isShutdown());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
        // 调用 shutdown() 方法之后
        System.out.println("threadPool.isShutdown()：" + threadPool.isShutdown());
    }

    /**
     * threadPool.isTerminated()
     * 必须先调用threadPool.shutdown() 否则一直是false
     * true: 所有任务是执行完毕
     * false: 所有任务否执行完毕
     */
    private static void test3() {
        // 10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 1000个任务
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 调用 shutdown() 方法之前
        System.out.println("threadPool.isTerminated()：" + threadPool.isTerminated());//false
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
        // 调用 shutdown() 方法之后
        System.out.println("threadPool.isTerminated()：" + threadPool.isTerminated());//false
    }

    /**
     * threadPool.awaitTermination(10L, TimeUnit.SECONDS)
     * 必须先调用threadPool.shutdown() 否则一直是false
     * 返回 (所有任务是否执行完毕||达到timeout||interrupted)
     * true: 所有任务执行完毕
     * false: 所有任务否执行完毕||达到timeout||interrupted
     */
    private static void test4() {
        // 10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 1000个任务
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 调用 shutdown() 方法之前
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
        // 调用 shutdown() 方法之后
        try {
            System.out.println("threadPool.awaitTermination()：" + threadPool.awaitTermination(10L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * threadPool.shutdownNow()
     * 执行中的任务，interrupt
     * 队列中的任务，作返回值
     */
    private static void test5() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ",被中断了");
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 执行shutdownNow()
        List<Runnable> runnableList = threadPool.shutdownNow();
        for (Runnable runnable : runnableList) {
            System.out.println(runnable);
        }
        System.out.println("threadPool.isShutdown(): " + threadPool.isShutdown());//true
    }
}
