package com.xcrj.concurrent.thread_local;

public class Example {
    //static final
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //主线程
        threadLocal.set(1);

        //子线程
        new Thread(() -> {
            threadLocal.set(2);
            System.out.println("Thread 1:" + threadLocal.get());
        }).start();

        //子线程
        new Thread(() -> {
            threadLocal.set(3);
            System.out.println("Thread 2:" + threadLocal.get());
        }).start();

        System.out.println("Main thread:" + threadLocal.get());

        threadLocal.remove();//使用完后，清空
        //threadLocal.set(null);//或者使用完后，将value设置为null
    }
}