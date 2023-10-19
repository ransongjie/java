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
public class ThreadTest2 {

    public static void main(String[] args) {
        new ThreadTest2().init();
    }

    public void init() {
        final Business business = new Business();
        //子线程执行50次任务
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            business.SubThread(i);
                        }
                    }
                }
        ).start();

        //主线程执行50次任务
        for (int i = 0; i < 50; i++) {
            business.MainThread(i);
        }
    }

    private class Business {
        //这里相当于定义了控制该谁执行的一个信号灯
        boolean bShouldSub = true;

        public synchronized void MainThread(int i) {
            if (bShouldSub) {
                try {
                    this.wait();//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 5; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = true;
            this.notify();//
        }

        public synchronized void SubThread(int i) {
            if (!bShouldSub) {
                try {
                    this.wait();//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = false;
            this.notify();//
        }
    }
}
