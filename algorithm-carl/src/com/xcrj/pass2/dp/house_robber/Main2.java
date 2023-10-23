package com.xcrj.pass2.dp.house_robber;

/**
 * https://leetcode.cn/problems/house-robber-ii/
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 */
public class Main2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        return Math.max(f(nums, 0, nums.length - 1), f(nums, 1, nums.length));
    }

    /**
     * @param nums
     * @param s    包括s
     * @param e    不包括e
     * @return
     */
    int f(int[] nums, int s, int e) {
        int[] dp = new int[e - s];
        dp[0] = nums[s];
        dp[1] = Math.max(nums[s], nums[s + 1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[s + i]);
        }
        //结果
        return dp[dp.length - 1];
    }
}
