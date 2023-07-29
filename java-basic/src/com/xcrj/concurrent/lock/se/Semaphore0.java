package com.xcrj.concurrent.lock.se;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户上厕所
 * 1个厕所10个坑位
 * new Semaphore(10); 10个许可证
 * semaphore.acquire(); 许可证数量>0则减1
 * semaphore.release(); 许可证数量+1
 */
public class Semaphore0 {
    public static void main(String[] args) {
        // 创建厕所
        Toilet toilet = new Toilet();
        // 创建用户
        int n = 6;
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(new User(i, toilet));
        }
        // 启动线程，用户上厕所
        for (int i = 0; i < n; i++) {
            Thread thread = threads[i];
            try {
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Toilet {
    // 锁住厕所
    private static final ReentrantLock lock = new ReentrantLock(true);
    // 坑位数量
    private static final Semaphore semaphore = new Semaphore(10, true);
    private boolean toilet[];

    public Toilet() {
        this.toilet = new boolean[10];
        // 初始所有坑位都可用
        Arrays.fill(toilet, true);
    }

    /**
     * 整个厕所加上可重入锁，获取可用坑位
     * 
     * @return 坑位编号
     */
    private int getPit() {
        int id = -1;
        lock.lock();
        try {
            // 遍历找没人的坑位
            for (int i = 0; i < 10; i++) {
                if (toilet[i]) {
                    toilet[i] = false;
                    id = i;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }

    /**
     * 生产者消费者
     * 可重入锁获取厕所可用坑位
     * 信号量使用坑位
     * 
     * @param userId
     */
    public void usePit(int userId) {
        try {
            // 许可证数量>0则减1
            semaphore.acquire();
            int resourceId = getPit();
            System.out.println("用户：" + userId + "正在使用坑位：" + resourceId);
            Thread.sleep(100);
            // 坑位使用完退出坑位
            toilet[resourceId] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 许可证数量+1
            semaphore.release();
        }
    }
}

class User implements Runnable {
    private int userId;
    private Toilet toilet;

    public User(int userId, Toilet toilet) {
        this.userId = userId;
        this.toilet = toilet;
    }

    @Override
    public void run() {
        System.out.print("userId:" + userId + "准备上厕所...\n");
        toilet.usePit(userId);
        System.out.print("userId:" + userId + "使用厕所完毕...\n");
    }
}
