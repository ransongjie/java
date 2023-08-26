package com.xcrj.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * 分割等和子集
 */
public class Main8 {
    /**
     * 转化为0-1背包问题
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        /**
         * 问题转换
         * 背包：target
         * 物品：nums中的元素值
         * - 重量：nums中的元素值
         * - 价值：nums中的元素值
         * 物品要么选要么不选只有01两种状态
         * 物品只能选1次
         */
        int sum = 0, target, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) return false;// 奇数分割等和子集
        target = sum / 2;
        // dp数组
        // dp[j]=背包承重为j时的最大价值
        int[] dp = new int[target + 1];
        // 初始状态
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {// 倒序
                dp[j] = Math.max(nums[i] + dp[j - nums[i]], dp[j]);
            }
            // 背包承重为sum/2时背包中选择物品总价值是sum/2，物品的重量=价值
            if (dp[target] == target) return true;
        }
        // 结果
        return false;
    }
}
