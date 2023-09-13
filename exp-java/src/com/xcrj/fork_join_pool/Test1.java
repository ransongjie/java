package com.xcrj.fork_join_pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool计算1~10000的数
 * 类：ForkJoinPool、RecursiveTask
 * 过程：拆分任务》执行任务fork》获取结果join
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumRecursiveTask(1000, 1, 10000);
        pool.submit(task);
        System.out.println(task.get());
    }
}

class SumRecursiveTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    final int threshold;
    final int start;
    final int end;

    SumRecursiveTask(int threshold, int start, int end) {
        this.threshold = threshold;
        this.start = start;
        this.end = end;
    }

    /**
     * 拆分任务》执行任务fork》获取结果join
     *
     * @return
     */
    @Override
    protected Integer compute() {
        //计算量小于1000，只有1个任务，并返回结果
        if (end - start < this.threshold) {
            System.out.println(Thread.currentThread().getName() + " 开始执行: " + start + "-" + end);
            int sum = 0;
            for (int i = start; i <= end; i++)
                sum += i;
            return sum;
        } else {
            //计算量大于1000，拆分为2个任务
            SumRecursiveTask task1 = new SumRecursiveTask(threshold, start, (start + end) / 2);
            SumRecursiveTask task2 = new SumRecursiveTask(threshold, (start + end) / 2 + 1, end);
            //执行任务
            task1.fork();
            task2.fork();
            //获取结果
            return task1.join() + task2.join();
        }
    }
}
