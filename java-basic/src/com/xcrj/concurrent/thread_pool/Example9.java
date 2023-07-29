package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newWorkStealingPool()
 */
public class Example9 {
    public static void main(String[] args) {
        /**
         * new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
         * ForkJoinPool线程池中的线程是守护线程，主线程任务执行完毕，所有守护线程都将停止
         * new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true) 
         */
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程：" + Thread.currentThread().getName() + " 执行耗时任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 主线程执行任务
        for (int i = 0; i < 1; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("主线程执行任务" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 关闭线程池，否则线程池会维持核心线程数一直运行
        executorService.shutdown();
    }
}
