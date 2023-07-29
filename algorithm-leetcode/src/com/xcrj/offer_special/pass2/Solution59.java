package com.xcrj.offer_special.pass2;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer II 059. 数据流的第 K 大数值
 * 请实现 KthLargest类：
 * - KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * - int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */
public class Solution59 {
    /**
     * 优先队列+限制容量
     */
    class KthLargest {
        Queue<Integer> que;
        int kl;
        public KthLargest(int k, int[] nums) {
            // xcrj 默认从小到达排列
            que=new PriorityQueue<>();
            kl=k;
            for(int num:nums){
                que.offer(num);
            }
        }

        public int add(int val) {
            que.offer(val);
            while(que.size()>kl) que.poll();
            return que.peek();
        }
    }
}
