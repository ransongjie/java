package com.xcrj.concurrent.thread_pool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(corePoolSize)/固定延迟周期性执行scheduleWithFixedDelay()
 */
public class Example7 {
    public static void main(String[] args) {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L, NANOSECONDS
         * new DelayedWorkQueue(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        /**
         * scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
         * initialDelay：初始延迟时间
         * delay：延迟时间
         * 任务执行完成之后延迟delay时间再执行下一轮
         */
        for (int i = 0; i < 3; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
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
            }, 1, 1, TimeUnit.SECONDS);
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
