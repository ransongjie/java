package com.xcrj.concurrent.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture2 {
    public static void main(String[] args) {
        SunWuKong.gogogo();
    }

    /**
     * 并行任务-anyOf()
     * 孙悟空现口渴，任务1-找果汁喝，任务2-找矿泉水喝，任务3-搞学习
     * 任务1或者任务2执行完毕孙悟空都能解渴；任务1或者任务2执行完毕后，任务3才能执行
     * 孙悟空分出3个分身（线程），分身1-找果汁喝，分身2-找矿泉水喝，分身3-搞学习；分身1找到果汁喝了或者分身2找到矿泉水喝了，分身3才能搞学习
     */
    static class SunWuKong {
        public static void gogogo() {
            CompletableFuture cf1 = CompletableFuture.runAsync(() -> {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (Exception ex) {

                }
                SunWuKong sunWuKong1 = new SunWuKong();
                sunWuKong1.duty1();
            });

            CompletableFuture cf2 = CompletableFuture.runAsync(() -> {
                SunWuKong sunWuKong2 = new SunWuKong();
                sunWuKong2.duty2();
            });

            CompletableFuture all = CompletableFuture.anyOf(cf1, cf2).thenRun(() -> {
                SunWuKong sunWuKong3 = new SunWuKong();
                sunWuKong3.duty3();
            });

            try {
                // 等待获取返回结果，会阻塞main线程，外部异常处理
                all.get();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        private void duty1() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",找果汁喝");
        }

        private void duty2() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",找矿泉水喝");
        }

        private void duty3() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",搞学习");
        }
    }

}
