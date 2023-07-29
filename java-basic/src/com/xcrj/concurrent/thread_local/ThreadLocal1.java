package com.xcrj.concurrent.thread_local;

/**
 * 每个Thread都持有1个ThreadLocalMap对象，不同Thead的ThreadLocalMap对象不同
 * ThreadLocalMap的key是ThreadLocal对象，value是用户设置的值
 * ThreadLocalMap的key是弱引用，gc时一定会回收，因此使用static final修饰
 */
public class ThreadLocal1 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
}

class MyThreadLocal {
    //static final
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "default value";
        }
    };

    public static void set(String value) {
        threadLocal.set(value);
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        //线程，往ThreadLocal中存入值
        MyThreadLocal.set("value for thread " + Thread.currentThread().getName());
        //线程，从ThreadLocal中取值
        System.out.println("Thread " + Thread.currentThread().getName() + " variable value: " + MyThreadLocal.get());
        MyThreadLocal.remove();
    }
}