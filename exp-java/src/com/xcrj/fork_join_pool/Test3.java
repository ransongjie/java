package com.xcrj.fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool计算20 Fibonacci
 */
public class Test3 {
    public static void main(String[] args) {
        // 最大并发数4
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Fibonacci fibonacci = new Fibonacci(20);
        long startTime = System.currentTimeMillis();
        Integer result = forkJoinPool.invoke(fibonacci);//
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join fibonacci: " + result + ", " + (endTime - startTime) + " ms.");
    }
}

// 官方API文档示例
class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();//
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();//
    }
}
