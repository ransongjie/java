package com.xcrj.dp.max_array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 * 递增子序列的最大长度
 * 给你一个整数数组 nums ，找到其中最长递增子序列的长度。
 * 子序列可以不连续
 */
public class Main1 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]=以nums[i]结尾的递增子序列的最大长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);//以dp[i]结尾的子序列至少就是自己
        int max = 1;
        for (int i = 1; i < n; i++) {// 0 只有1个字符的子序列已经求过了
            //! 结尾nums[i]和前面0~i的整数比较
            for (int j = 0; j < i; j++) {
                // dp[i]=nums[i]与nums[0~i]的每1个元素比较，nums[i]>nums[j]
                // dp[j]=以nums[j]结尾的子序列可以接上1个nums[i]，nums[j], nums[i]
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
