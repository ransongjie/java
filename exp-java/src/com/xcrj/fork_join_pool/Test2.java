package com.xcrj.fork_join_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Test2 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumRecursiveTaskb task = new SumRecursiveTaskb(1000, 1, 10000);
        Integer result = forkJoinPool.invoke(task);
        System.out.println("Result: " + result);
    }
}

/**
 * invokeAll() 在当前任务内部调用的，方法用于并行执行一组子任务，并等待它们全部完成，返回所有子任务的结果 (List<Future<T>>)
 * fork() 方法用于启动一个子任务的执行，然后继续执行当前任务的剩余部分
 */
class SumRecursiveTaskb extends RecursiveTask<Integer> {
    private final int threshold;
    private final int start;
    private final int end;

    SumRecursiveTaskb(int threshold, int start, int end) {
        this.threshold = threshold;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start <= threshold) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            SumRecursiveTaskb leftTask = new SumRecursiveTaskb(threshold, start, mid);
            SumRecursiveTaskb rightTask = new SumRecursiveTaskb(threshold, mid + 1, end);

            List<RecursiveTask<Integer>> tasks = new ArrayList<>();
            tasks.add(leftTask);
            tasks.add(rightTask);

            this.invokeAll(tasks);//
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }
}
