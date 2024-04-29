package com.xcrj.coins;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/coin-change/
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 1种面值的硬币可以选择多次，无限次
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 完全背包问题
 */
public class CoinMin {
    /**
     * 凑成总金额所需最少硬币个数
     * 凑成部分金额所需最少硬币个数
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int bagSize = amount;
        int[] weight = coins;
        int[] value = coins;
        //dp[总金额为i]=最小硬币个数
        int[] dp = new int[bagSize + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;//dp[总金额为0]=1个硬币也不需要
        //组合问题，顺序无所谓
        for (int i = 0; i < weight.length; i++) {
            for (int j = weight[i]; j < bagSize + 1; j++) {
                if (dp[j - weight[i]] != Integer.MAX_VALUE) {
                    //凑成金额j，不要硬币weight[i]，要硬币weight[i]
                    dp[j] = Math.min(dp[j], 1 + dp[j - weight[i]]);
                }
            }
        }
        return dp[bagSize] == Integer.MAX_VALUE ? -1 : dp[bagSize];
    }
}
