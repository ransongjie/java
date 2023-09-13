package com.xcrj.dp.knapsack_complete;

/**
 * https://leetcode.cn/problems/coin-change-ii/submissions/
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 */
public class Main2 {
    /**
     * 必须先物品再遍历背包承重
     * 反之，
     * 背包i中放入了硬币x,y
     * 背包j中也放入了硬币x,y 产生相同的组合
     */
    public int change(int amount, int[] coins) {
        // dp[币值]=组合数
        int[] dp = new int[amount + 1];
        // 背包承重为0,1个硬币也不放入背包也认为是一种情况
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                // 不选硬币i 或 选择硬币i（取决于不要硬币i的方法数）
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    public int change2(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        // 只有coins[0]这1个硬币
        for (int i = 0; i < amount + 1; i += coins[0]) {// i += coins[0]
            dp[0][i] = 1;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                // 在背包承重为j的情况下能最多放k个硬币i
                for (int k = 0; k * coins[i] <= j; k++) {
                    // 第i个硬币一定要选，组合数量取决于dp[i-1][j-k*coins[i]]
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
                }
            }
        }
        return dp[coins.length][amount];
    }

}

