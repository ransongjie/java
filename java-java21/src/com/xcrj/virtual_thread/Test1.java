package com.xcrj.virtual_thread;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;

public class Test1 {
    public static void main(String[] args) {
        test1("xcrj",()->{
            System.out.println("I'am going to take a bath");
            try {
                sleep(500l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'am done with the bath");
        });
    }

    private static Thread test1(String name, Runnable runnable) {
        return Thread.ofVirtual().name(name).start(runnable);
    }


    private static void test2() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var bathTime = executor.submit(() -> {
                System.out.println("I'am going to take a bath");
                try {
                    sleep(Duration.ofMillis(500L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'am done with the bath");
            });
            var boilingWater = executor.submit(() -> {
                System.out.println("I'am going to boil some water");
                try {
                    sleep(Duration.ofMillis(500L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'am done with the water");
            });
            bathTime.get();
            boilingWater.get();
        }
    }

    private static void test3() throws ExecutionException, InterruptedException {
        final ThreadFactory threadFactory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
            var bathTime = executor.submit(() -> {
                System.out.println("I'am going to take a bath");
                try {
                    sleep(Duration.ofMillis(500L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'am done with the bath");
            });
            var boilingWater = executor.submit(() -> {
                System.out.println("I'am going to boil some water");
                try {
                    sleep(Duration.ofMillis(500L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'am done with the water");
            });
            bathTime.get();
            boilingWater.get();
        }
    }
}
