package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 008. 和大于等于target的连续最短子数组
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 元素和>=target&&元素构成的子序列是连续的$$子序列的长度最短
 */
public class Solution8 {
    /**
     * 滑动窗口(双指针)
     * ！！！使用不同的指针达到不同的目的
     * i j开始都指向0
     * 先移动j 探求sum>=target
     * 再移动i 探求sum<target
     * 两相合力之下求出满足条件的长度最小的子序列
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        //
        while (j < nums.length) {
            sum += nums[j];
            while (sum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i];
                // 移动i 探求sum<target
                i++;
            }
            // 移动j 探求sum>=target
            j++;
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        System.out.println(solution8.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
