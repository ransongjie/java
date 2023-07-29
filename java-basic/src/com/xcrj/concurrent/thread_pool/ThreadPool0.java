package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参考：https://ransongjie.blog.csdn.net/article/details/125897377
 * 
 * ThreadPoolExecutor 自定义线程池
 * 执行器(Executor, ExecutorService)，
 * 线程池(ThreadPoolExecutor, ScheduledThreadPoolExecutor, ForkJoinPool)，
 * 线程，线程创建工厂
 * 任务队列(无界队列-LinkedBlockingQueue, 同步队列-SynchronousQueue, 延迟队列-DelayedWorkQueue)，
 * 任务(Runnable, Callable-FutureTask)，任务拒绝策略
 * 关闭线程池
 */
public class ThreadPool0 {
    /**
     * 自定义线程池（阿里推荐）
     * 1. 明确使用什么线程池
     * 2. 得到线程数：明确I/O密集型还是CPU密集型
     * 3. 确定任务队列
     * 4. 线程工厂
     * 5. 任务拒绝策略
     * <p>
     * 线程数(CPU密集型)=CPU核心数的1到2倍=2*CPU核心数
     * 线程数(I/O密集型)=CPU核心数 × (1+平均等待时间/平均工作时间)，IO时间越长线程数量越大=1+CPU核心数
     * java获取CPU核心数：Runtime.getRuntime().availableProcessors()
     */
    public static void main(String[] args) {
        /**
         * corePoolSize：核心线程数，线程池维持的线程数
         * maximumPoolSize：池中最多线程数
         * keepAliveTime：大于核心线程数的线程存活时间
         * unit：存活时间单位
         * workQueue：任务队列
         * threadFactory：线程工厂
         * RejectedExecutionHandler：任务拒绝策略处理器
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors() * 2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread th = new Thread(r, "threadName");
                        if (th.getPriority() != Thread.NORM_PRIORITY)
                            th.setPriority(Thread.NORM_PRIORITY);
                        return th;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        // 日志
                        System.out.println("任务" + r.toString() + "被拒绝");
                    }
                });

        for (int i = 1; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
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
        threadPoolExecutor.shutdown();
    }

}