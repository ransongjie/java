package com.xcrj.interview150.dp1;

/**
 * https://leetcode.cn/problems/house-robber
 * 房屋偷盗
 */
public class Solution1 {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            //2种选择
            //偷第i-1家，偷第i-2家
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n - 1];
    }
}
