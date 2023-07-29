package com.xcrj.concurrent.lock.cb;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 客人都到齐了才能开席
 * public CyclicBarrier(int parties, Runnable barrierAction) 
 * countDownLatch.await();
 */
public class CyclicBarrier0 {
    private static final int count = 8;
    // public CyclicBarrier(int parties, Runnable barrierAction) 达到栅栏后的行为
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> {
        System.out.println("客人到齐了，开席");
    });

    public static void main(String[] args) {
        for (int i = 0; i < count; ++i) {
            new Thread(new Guest(i, cyclicBarrier)).start();
        }
    }
}

/**
 * 客人到齐了可以开席
 */
class Guest implements Runnable {
    private final int id;
    private final CyclicBarrier cyclicBarrier;

    Guest(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    /**
     * 客人到齐了吗
     */
    @Override
    public void run() {
        try {
            System.out.println("客人: " + id + "，赶路中");
            Thread.sleep(1000);
            System.out.println("客人: " + id + "，入座");
            // 客人是否到齐
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}