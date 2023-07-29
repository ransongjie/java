package com.xcrj.concurrent.key_word;

/**
 * 原子性
 * 可见性
 * 有序性
 * Volatile 保证可见性和有序性，不保证原子性
 */
public class Volatile0 {
    private volatile boolean flag = false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile0 volatile0 = new Volatile0();
        // 创建一个线程循环检查flag的值
        new Thread(() -> {
            while (!volatile0.flag) {
                System.out.println("没达标");
            }
            System.out.println("达标");
        }).start();

        // 主线程1S之后修改标志的值，flag的值立马刷新到线程的工作内存
        Thread.sleep(2000);
        volatile0.setFlag(true);
    }
}
