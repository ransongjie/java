package com.xcrj.concurrent.thread_pool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executors.newSingleThreadExecutor/submit(Callable task)有返回值
 */
public class Example3 {
    	// Executors.newSingleThreadExecutor/submit(Callable<T> task)
        public static void main(String[] args) throws ExecutionException, InterruptedException {
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
             * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
             * corePoolSize=1, maximumPoolSize=1, keepAliveTime=0L, TimeUnit.MILLISECONDS
             * new LinkedBlockingQueue<Runnable>(){this(Integer.MAX_VALUE);}
             */
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            /**
             *  submit(Callable<T> task)
             *  submit(Runnable task, T result)
             *  submit(Runnable task)
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
}
