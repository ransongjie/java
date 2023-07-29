package com.xcrj.concurrent.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture1 {
    public static void main(String[] args) {
        SunWuKong.gogogo();
    }

    /**
     * 并行任务-runAfterBothAsync()
     * 孙悟空的一天，任务1-打游戏，任务2-搞学习，任务3-吃饭
     * 任务1和任务2之间没有任何关系可以并行，任务3需要等到任务1和任务2都完成之后才能执行
     * 孙悟空分出2个分身（线程），分身1-打游戏，分身2-搞学习，分身3-吃午饭；分身1把游戏打完了，分身2把学习搞完了，分身3才允许吃饭
     */
    static class SunWuKong {
        public static void gogogo() {
            try {
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.currentThread().sleep(5000);
                    } catch (Exception ex) {

                    }
                    SunWuKong sunWuKong1 = new SunWuKong();
                    sunWuKong1.duty1();
                }).runAfterBothAsync(CompletableFuture.runAsync(() -> {
                    SunWuKong sunWuKong2 = new SunWuKong();
                    sunWuKong2.duty2();
                }), () -> {
                    SunWuKong sunWuKong3 = new SunWuKong();
                    sunWuKong3.duty3();
                }).get();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        private void duty1() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",打游戏");
        }

        private void duty2() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",搞学习");
        }

        private void duty3() {
            System.out.println("分身：" + Thread.currentThread().getName() + ",吃饭");
        }
    }

}
