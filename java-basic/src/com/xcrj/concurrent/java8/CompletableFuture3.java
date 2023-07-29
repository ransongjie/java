package com.xcrj.concurrent.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture3 {
    public static void main(String[] args) {
        SunWuKong.gogogo();
    }

    /**
     * 串行结果-thenApplyAsync()
     * 孙悟空做蛋炒饭，任务1-炒鸡蛋，任务2-炒饭
     * 任务2需要任务1的执行结果，任务2在不需要任务1的结果之前，任务1和任务2仍然可以并行执行
     * 孙悟空分出2个分身（线程），分身1-炒鸡蛋，分身2-炒饭；分身1-把炒好的鸡蛋放到分身2的炒饭里，分身2-搅拌均匀
     */
    static public class SunWuKong {
        public static void gogogo() {
            try {
                CompletableFuture.supplyAsync(() -> {
                    SunWuKong sunWuKong1 = new SunWuKong();
                    return sunWuKong1.duty1();
                }).thenAcceptAsync((o) -> {
                    SunWuKong sunWuKong2 = new SunWuKong();
                    String str = sunWuKong2.duty2();
                    System.out.println("把" + o + "放到" + str);
                }).get();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        private String duty1() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",炒鸡蛋");
            return "炒鸡蛋";
        }

        private String duty2() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",炒饭");
            return "炒饭";
        }
    }

}
