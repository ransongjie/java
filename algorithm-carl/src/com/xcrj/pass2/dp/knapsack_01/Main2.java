package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * 分割等和子集
 */
public class Main2 {
    /**
     * 转化为0-1背包问题
     * 物品=nums元素
     * bagSize=sum/2
     * weight=nums
     * value=nums
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 奇数无法构成等和子集
        if ((sum & 1) == 1) return false;
        // 01背包问题
        int bagSize = sum / 2;
        int[] weight = nums;
        int[] value = nums;
        int n = weight.length;
        // dp数组
        int[] dp = new int[bagSize + 1];
        // 初始状态
        // 只有第1个物品
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        // 状态转移
        // 先物品再背包
        for (int i = 1; i < n; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                // 不放入第i件物品，放入第i件物品
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
            if (dp[bagSize] == bagSize) return true;
        }

        return false;
    }
}
