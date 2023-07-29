package com.xcrj.offer_special.pass1;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer II 059. 数据流的第 K 大数值
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素
 * 请实现 KthLargest类：
 * - KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * - int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */
public class Solution59 {
    /**
     * 优先队列
     */
    class KthLargest {
        Queue<Integer> queue;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            // 默认，值越小越靠前
            this.queue = new PriorityQueue<>();
            for (int num : nums) {
                this.queue.offer(num);
            }
        }

        /**
         * 添加元素到优先队列
         * 若优先队列中的元素数量超过k则出队
         */
        public int add(int val) {
            this.queue.offer(val);
            while (this.queue.size() > k) {
                this.queue.poll();
            }
            return this.queue.peek();
        }
    }
}
