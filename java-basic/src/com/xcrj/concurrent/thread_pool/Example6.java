package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(corePoolSize)/固定间隔周期性执行scheduleAtFixedRate()
 */
public class Example6 {
    public static void main(String[] args) {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L, NANOSECONDS
         * new DelayedWorkQueue(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        /**
         * scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
         * initialDelay：初始延迟时间
         * period：执行间隔
         * 执行耗时>执行间隔：任务执行完成后立马开始执行下一轮任务
         * 执行耗时<等待时间：在任务执行完成后等待(等待时间-执行耗时)，再执行下一轮任务
         */
        for (int i = 0; i < 3; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 1, 2, TimeUnit.SECONDS);
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
        scheduledExecutorService.shutdown();
    }
}
