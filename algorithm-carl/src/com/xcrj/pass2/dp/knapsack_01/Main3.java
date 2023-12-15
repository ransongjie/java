package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/last-stone-weight-ii/
 * 最后一块石头的重量
 */
public class Main3 {
    /**
     * 尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小
     * 转化为0-1背包问题
     * 物品=stones元素
     * bagSize=sum/2
     * weight=stones
     * value=stones
     * 
     * sum/2承重下的最大价值(重量)
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone :
                stones) {
            sum += stone;
        }
        // 01背包问题
        int bagSize = sum / 2;
        int[] weight = stones;
        int[] value = stones;
        int n = weight.length;
        // dp数组
        int[] dp = new int[bagSize + 1];
        // 初始状态
        // 只有第1个物品
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }
        // 结果
        return sum - 2 * dp[bagSize];
    }
}
