package com.xcrj.sync;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * main线程和son线程交替运行
 * <p>
 * 子线程执行5次任务，接着主线程执行10任务，
 * 接着又回到子线程执行5次任务，接着再回到主线程又执行10任务，
 * 如此循环50次。
 * <p>
 * 1. 主线程和子线程都 执行次数 50次
 * 2. bShouldSub变量+lock+subThreadCondition.await()/subThreadCondition.signal() 控制主线程和子线程执行顺序
 * 3. 子线程先执行5次任务
 */
public class ThreadTest2b {
    private static Lock lock = new ReentrantLock();
    private static Condition subThreadCondition = lock.newCondition();
    private static boolean bBhouldSubThread = false;

    public static void main(String[] args) {
        // 子线程使用线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //执行50次
                for (int i = 0; i < 50; i++) {
                    lock.lock();//
                    try {
                        if (!bBhouldSubThread) {
                            subThreadCondition.await();//
                        }
                        // 子线程先执行5次
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName() + ",j=" + j);
                        }
                        bBhouldSubThread = false;
                        subThreadCondition.signal();//
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();//
                    }
                }
            }
        });
        threadPool.shutdown();

        //执行50次
        for (int i = 0; i < 50; i++) {
            lock.lock();//
            try {
                if (bBhouldSubThread) {
                    subThreadCondition.await();//
                }
                //主线程执行10次
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + ",j=" + j);
                }
                bBhouldSubThread = true;
                subThreadCondition.signal();//
            } catch (Exception e) {
            } finally {
                lock.unlock();//
            }
        }
    }
}
