package com.xcrj.knapsack_01;

/**
 * https://leetcode.cn/problems/last-stone-weight-ii/
 * 石头相撞，剩下最后一块石头的重量
 */
public class MainMax3 {
    /**
     * 尽量让两堆石头的重量接近》让一堆石头的重量接近sum/2》选择的石头的重量和接近sum/2
     * weight=stones
     * value=stones
     * bagSize=sum/2
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s :
                stones) {
            sum += s;
        }
        //问题转换
        int[] weight = stones;
        int[] value = stones;
        int bagSize = sum / 2;
        //dp数组
        int[] dp = new int[bagSize + 1];
        //初始状态
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        //状态转移
        for (int i = 1; i < stones.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }
        //结果
        return sum - 2 * dp[bagSize];
    }
}
