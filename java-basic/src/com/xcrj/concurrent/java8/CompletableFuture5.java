package com.xcrj.concurrent.java8;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFuture5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test1();
        // test2();
    }

    /**
     * join()-替代allOf
     * allOf()：没有返回值；任务执行顺序-并行
     * join()：阻塞main线程直到CF执行完成
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "xcrj1");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "xcrj2");
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "xcrj3");
        CompletableFuture.allOf(cf1, cf2, cf3);
        StringJoiner sj=new StringJoiner(",");
        sj.add(cf1.get()).add(cf2.get()).add(cf3.get());
        String result=sj.toString();
        System.out.println("result: "+result);
    }

    public static void test2() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "xcrj1");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "xcrj2");
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "xcrj3");
        // 并行流
        String result = Stream.of(cf1, cf2, cf3).parallel().map(CompletableFuture::join)
                .collect(Collectors.joining(", "));
        System.out.println("result: " + result);
    }

}
