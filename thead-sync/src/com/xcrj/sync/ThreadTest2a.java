package com.xcrj.sync;

/**
 * main线程和son线程交替运行
 * <p>
 * 子线程执行5次任务，接着主线程执行10任务，
 * 接着又回到子线程执行5次任务，接着再回到主线程又执行10任务，
 * 如此循环50次。
 * <p>
 * 1. 主线程和子线程都 执行次数 50次
 * 2. bShouldSub变量+synchronized+this.wait()/notify() 控制主线程和子线程执行顺序
 * 3. 子线程先执行5次任务
 */
public class ThreadTest2a {

    private static boolean bShouldMain = false;

    public static void main(String[] args) {
        //子线程
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // 运行50次
                        for (int i = 0; i < 50; i++) {
                            synchronized (ThreadTest2a.class) {
                                if (bShouldMain) {
                                    try {
                                        ThreadTest2a.class.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                //子线程先执行5次
                                for (int j = 0; j < 5; j++) {
                                    System.out.println(
                                            Thread.currentThread().getName() +
                                                    "i=" + i + ",j=" + j);
                                }
                                bShouldMain = true;
                                ThreadTest2a.class.notify();
                            }
                        }
                    }
                }
        ).start();

        //主线程 运行50次
        for (int i = 0; i < 50; i++) {
            synchronized (ThreadTest2a.class) {
                if (!bShouldMain) {
                    try {
                        ThreadTest2a.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //主线程后执行5次
                for (int j = 0; j < 5; j++) {
                    System.out.println(
                            Thread.currentThread().getName() +
                                    "i=" + i + ",j=" + j);
                }
                bShouldMain = false;
                ThreadTest2a.class.notify();
            }
        }
    }
}
