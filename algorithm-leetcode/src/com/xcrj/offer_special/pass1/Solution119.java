package com.xcrj.offer_special.pass1;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 119. 最长连续序列，不要求在原序列中有序
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 分析：在nums中大1的最长序列，不要求在nums中大1的数字是连续的
 */
public class Solution119 {
    // 使用hash表存储，逐个数字寻找大1下一个数，从小1的数字开始找，避免从大1的数字开始找的重复寻找
    public int longestConsecutive(int[] nums) {
        // 使用hash表存储
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 逐个数字寻找大1下一个数
        int maxR = 0;
        for (int num : nums) {
            // 从小1的数字开始找，避免从大1的数字开始找造成的重复寻找
            if (!set.contains(num - 1)) {
                // 找到大1的数字则接着寻找
                int currNum = num;
                int currR = 0;
                while (set.contains(currNum)) {
                    currNum++;
                    currR++;
                }
                // 对比最长序列
                maxR = Math.max(maxR, currR);
            }
        }

        return maxR;
    }
}