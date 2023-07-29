package com.xcrj.concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor/execute(Runnable command)无返回值
 */
public class Example2 {
        // Executors.newSingleThreadExecutor/execute(Runnable command)
        public static void main(String[] args) {
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
             * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
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
}
