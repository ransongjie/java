package com.xcrj.concurrent.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture4 {
    public static void main(String[] args) {
        SunWuKong.gogogo();
    }

    /**
     * 串行任务-thenApply()
     * 孙悟空参加铁人三项，必须是同1个人完成“跑步”，“骑单车”，“游泳”
     * 孙悟空分出1个分身（线程），分身先跑步，再骑单车，最后游泳
     * 需要先执行第1个任务再执行第2个任务，直接单线程，一个个任务执行
     */
    static public class SunWuKong {
        public static void gogogo() {
            try {
                CompletableFuture.supplyAsync(() -> {
                    SunWuKong sunWuKong1 = new SunWuKong();
                    return sunWuKong1.duty1();
                }).thenApply((o) -> {
                    System.out.println("完成" + o);
                    SunWuKong sunWuKong2 = new SunWuKong();
                    return sunWuKong2.duty2();
                }).thenApply((o) -> {
                    System.out.println("完成" + o);
                    SunWuKong sunWuKong3 = new SunWuKong();
                    return sunWuKong3.duty3();
                }).get();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        private String duty1() {
            try {
                Thread.currentThread().sleep(5000);
            } catch (Exception ex) {
            }
            System.out.println("分身：" + Thread.currentThread().getName() + ",跑步");
            return "跑步";
        }

        private String duty2() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",骑单车");
            return "骑单车";
        }

        private String duty3() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",游泳");
            return "游泳";
        }
    }

}
