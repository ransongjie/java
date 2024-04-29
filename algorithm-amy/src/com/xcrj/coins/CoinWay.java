package com.xcrj.coins;

/**
 * https://leetcode.cn/problems/coin-change-ii/submissions/
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * 求组合数
 * 完全背包
 */
public class CoinWay {
    public int change(int amount, int[] coins) {
        //dp[金额i]=组合数量
        int[] dp = new int[amount + 1];
        dp[0] = 1;//金额0，1个硬币也不要，也是一种方法
        //求组合，与顺序无关
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                //之前的dp[j-coins[i]]种方法，都选择coins[i]硬币，等于j
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
