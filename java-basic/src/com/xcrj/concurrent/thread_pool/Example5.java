package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool()
 */
public class Example5 {
    public static void main(String[] args) {
        /**
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
         * corePoolSize=0, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=60L, TimeUnit.SECONDS
         * new SynchronousQueue<Runnable>(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 主线程执行任务
        for (int i = 0; i < 3; i++) {
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
