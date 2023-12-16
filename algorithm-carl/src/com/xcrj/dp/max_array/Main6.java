package com.xcrj.dp.max_array;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 * 最大子数组的和
 * 具有最大和的连续子数组（子数组最少包含一个元素）
 */
public class Main6 {
    public static int maxSubArray(int[] nums) {
        //dp[i]=以nums[i]结尾的最大子数组的和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 到了nums[i]要么变大，要么变小
            // =max(以nums[i-1]结尾的最大子数组的和+nums[i],nums[i]);
            // 子数组，需要连续
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
