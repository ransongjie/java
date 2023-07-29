package com.xcrj.concurrent.thread_pool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors工具类创建线程
 */
public class ThreadPool1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        // test6();
    }

    // Executors.newSingleThreadExecutor/execute(Runnable command) duty无返回值
    public static void test1() {
        Runnable duty = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Runnable执行任务" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        /**
         * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new
         * LinkedBlockingQueue<Runnable>())
         * corePoolSize=1, maximumPoolSize=1, keepAliveTime=0L, TimeUnit.MILLISECONDS
         * new LinkedBlockingQueue<Runnable>(){this(Integer.MAX_VALUE);}
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // execute(Runnable command) 只能传入Runnable
        executorService.execute(duty);
        // 主线程执行任务
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("main执行任务" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 关闭线程池，否则线程池会维持核心线程数一直运行
        executorService.shutdown();
    }

    // Executors.newSingleThreadExecutor/submit(Callable<T> task) duty有返回值
    public static void test2() throws ExecutionException, InterruptedException {
        Callable<String> duty = new Callable<String>() {
            @Override
            public String call() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Callable执行任务" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Callable任务执行完成";
            }
        };
        /**
         * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new
         * LinkedBlockingQueue<Runnable>())
         * corePoolSize=1, maximumPoolSize=1, keepAliveTime=0L, TimeUnit.MILLISECONDS
         * new LinkedBlockingQueue<Runnable>(){this(Integer.MAX_VALUE);}
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /**
         * submit(Callable<T> task)
         * submit(Runnable task, T result)
         * submit(Runnable task)
         */
        Future<String> future = executorService.submit(duty);
        // 主线程执行任务
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("主线程执行任务" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线程执行完任务");
        LocalDateTime start = LocalDateTime.now();
        // futureTask.get()会阻塞调用线程，这里阻塞主线程
        System.out.println("查看Callable线程执行结果：" + future.get());
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("主线程运行完毕，主线程被阻塞时间ms：" + duration.toMillis());
        // 关闭线程池，否则线程池会维持核心线程数一直运行
        executorService.shutdown();
    }

    // Executors.newScheduledThreadPool(corePoolSize)/固定间隔周期性执行scheduleAtFixedRate()
    public static void test3() {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L,
         * NANOSECONDS
         * new DelayedWorkQueue(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        /**
         * scheduleAtFixedRate(Runnable command, long initialDelay, long period,
         * TimeUnit unit)
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

    // Executors.newScheduledThreadPool(corePoolSize)/固定延迟周期性执行scheduleWithFixedDelay()
    public static void test4() {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L,
         * NANOSECONDS
         * new DelayedWorkQueue(){this(false);} true-公平，线程FIFO获取任务，false-不公平，线程争抢获取任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        /**
         * scheduleWithFixedDelay(Runnable command, long initialDelay, long delay,
         * TimeUnit unit)
         * initialDelay：初始延迟时间
         * delay：延迟时间
         * 任务执行完成之后延迟delay时间再执行下一轮
         */
        for (int i = 0; i < 3; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    System.out.println(
                            "线程：" + Thread.currentThread().getName() + "开始执行任务时间" + LocalDateTime.now().getSecond());
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

    // Executors.newScheduledThreadPool(corePoolSize)/延迟执行1次schedule()
    public static void test5() {
        /**
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * corePoolSize=3, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=0L,
         * NANOSECONDS
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
                    System.out.println(
                            "线程：" + Thread.currentThread().getName() + "开始执行任务时间" + LocalDateTime.now().getSecond());
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

    /**
     * Executors.newWorkStealingPool()
     * newWorkStealingPool()本质上是ForkJoinPool
     * 工作窃取算法：在线程池中还有线程可以提供服务的时候帮忙分担一些已经被分配给某一个线程的耗时任务
     * 适合于有大量耗时任务的情况
     * ForkJoinPool线程池中的线程是守护线程，主线程任务执行完毕，所有守护线程都将停止
     */
    public static void test6() {
        /**
         * new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
         * ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
         * ForkJoinPool线程池中的线程是守护线程，主线程任务执行完毕，所有守护线程都将停止
         * new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
         * ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
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
