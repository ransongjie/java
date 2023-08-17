package com.xcrj.interview150.heap;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 数组中的第K个最大元素
 */
public class Solution01 {
    public int findKthLargest(int[] nums, int k) {
        //小根堆
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        for (int num :
                nums) {
            if (k-- > 0) {
                pque.offer(num);
            } else if (pque.peek() <= num) {
                pque.poll();//!!!
                pque.offer(num);
            }
        }
        return pque.peek();
    }
}
