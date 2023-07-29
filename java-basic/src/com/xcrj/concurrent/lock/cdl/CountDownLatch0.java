package com.xcrj.concurrent.lock.cdl;

import java.util.concurrent.CountDownLatch;

/**
 * 客人都到齐了才能开席
 * countDownLatch.countDown();
 * countDownLatch.await();
 */
public class CountDownLatch0 {
    private static final int count = 8;
    private static final CountDownLatch countDownLatch = new CountDownLatch(count);

    public static void main(String[] args) {
        for (int i = 0; i < count; ++i) {
            new Thread(new Guest(i, countDownLatch)).start();
        }

        //等待客人到齐，还有多少人没到
        try{
            countDownLatch.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("没有客人没到，开席");
    }
}

/**
 * 客人
 */
class Guest implements Runnable {
    private final int id;
    private final CountDownLatch countDownLatch;

    public Guest(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("客人: "+id+"，赶路中");
            Thread.sleep(1000);
            System.out.println("客人: "+id+"，入座");
            //CountDownLatch 需要收到-1
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}