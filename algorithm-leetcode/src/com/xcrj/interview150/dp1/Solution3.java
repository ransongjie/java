package com.xcrj.interview150.dp1;

import java.util.Arrays;

/**
 * 零钱兑换
 * https://leetcode.cn/problems/coin-change
 */
public class Solution3 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        //dp[金额]=硬币数量
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);//! 求最小值
        dp[0] = 0;//dp[金额0]=0个硬币
        int n = coins.length;

        //每个金额的每个币值都尝试
        for (int i = 1; i <= amount; i++) {//金额
            for (int j = 0; j < n; j++) {//币值
                if (i >= coins[j]) {
                    //! 当前金额i-前1个金额=每种币值
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
