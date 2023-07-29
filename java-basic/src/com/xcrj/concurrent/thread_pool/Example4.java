package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newFixedThreadPool(nThread)
 */
public class Example4 {
    public static void main(String[] args) {
        /**
         * new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
         * corePoolSize=3, maximumPoolSize=3, keepAliveTime=0L, TimeUnit.MILLISECONDS
         * new LinkedBlockingQueue<Runnable>(){this(Integer.MAX_VALUE);}
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Runnable duty = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(duty);
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
