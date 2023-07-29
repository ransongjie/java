package com.xcrj.concurrent.thread_pool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(corePoolSize)/延迟执行1次schedule()
 */
public class Example8 {
    public static void main(String[] args) {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L, NANOSECONDS
         * new DelayedWorkQueue(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        /**
         * schedule(Runnable command, long delay, TimeUnit unit)
         * delay：延迟时间
         * 在给定的延迟时间后执行1次任务
         */
        System.out.println("当前时间：" + LocalDateTime.now().getSecond());
        for (int i = 0; i < 3; i++) {
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName() + "开始执行任务时间" + LocalDateTime.now().getSecond());
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 2, TimeUnit.SECONDS);
        }

        // 主线程执行任务
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("主线程执行任务" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 关闭线程池，否则线程池会维持核心线程数一直运行
        scheduledExecutorService.shutdown();
    }
}
