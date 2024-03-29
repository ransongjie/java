package com.xcrj.knapsack_complete;

/**
 * https://leetcode.cn/problems/coin-change-ii/submissions/
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 */
public class Main2 {
    /**
     * 完全背包问题
     * weight=coins
     * value=coins
     * bagSize=amount
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        //dp数组
        int[] dp = new int[amount + 1];
        //初始状态
        dp[0] = 1;//背包承重为0，1个硬币也不放，也是一种方法
        //状态转移
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        //结果
        return dp[amount];
    }

    public int change2(int amount, int[] coins) {
        //dp数组
        int[][] dp = new int[coins.length][amount + 1];
        //初始状态
        for (int i = 0; i < amount + 1; i += coins[0]) {
            dp[0][i] = 1;//只有硬币0
        }
        //状态转移
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                for (int k = 0; j >= k * coins[i]; k++) {//k=0
                    dp[i][j] += dp[i - 1][j - k * coins[i]];//第i个硬币可以放入背包多次
                }
            }
        }
        //结果
        return dp[coins.length - 1][amount];
    }
}
