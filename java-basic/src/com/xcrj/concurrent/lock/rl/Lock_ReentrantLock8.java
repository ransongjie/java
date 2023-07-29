package com.xcrj.concurrent.lock.rl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家进餐
 * 破坏请求保持，持有左侧筷子，拿不到右侧筷子，必须释放左侧的筷子
 * 哲学家是线程；筷子是锁，哲学家要想吃饭必须获得左筷子和右筷子
 */
public class Lock_ReentrantLock8 {
    public static void main(String[] args) {
        // 5只筷子
        Chopstick ck1 = new Chopstick(1);
        Chopstick ck2 = new Chopstick(2);
        Chopstick ck3 = new Chopstick(3);
        Chopstick ck4 = new Chopstick(4);
        Chopstick ck5 = new Chopstick(5);

        // 5个哲学家
        Philosopher ph1=new Philosopher("xcrj1", ck1, ck2);
        Philosopher ph2=new Philosopher("xcrj2", ck2, ck3);
        Philosopher ph3=new Philosopher("xcrj3", ck3, ck4);
        Philosopher ph4=new Philosopher("xcrj4", ck4, ck5);
        Philosopher ph5=new Philosopher("xcrj5", ck5, ck1);
        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();
        ph5.start();
    }
}

class Chopstick extends ReentrantLock {
    int id;// 筷子编号

    Chopstick(int id) {
        this.id = id;
    }
}

class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;

    Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() throws InterruptedException {
        System.out.println(super.getName()+"吃饭中, 筷子"+left.id+"和"+right.id);
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        while (true) {
            if (this.left.tryLock()) {
                // 尝试获取到了左侧筷子，尝试获取右侧筷子
                try {
                    if (this.right.tryLock()) {
                        try {
                            eat();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            this.right.unlock();
                        }
                    }
                } finally {
                    // 获取不到右侧的筷子将释放已经持有的左侧的筷子
                    left.unlock();
                }
            }
        }
    }
}