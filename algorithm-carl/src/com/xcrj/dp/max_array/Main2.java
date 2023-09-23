package com.xcrj.dp.max_array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 * 最长连续递增子序列
 * 递增 且 相连
 */
public class Main2 {
    public static int findLengthOfLCIS(int[] nums) {
        // dp[i]=以nums[i]结尾的最长连续递增子序列
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // dp[i-1]=以nums[i-1]结尾的连续递增子序列的长度
            // nums[i]>nums[i-1] 则长度可以扩充1个 nums[i-1], nums[i]
            if (nums[i] > nums[i - 1]) dp[i] = dp[i - 1] + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
