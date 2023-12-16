package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 1支股票买卖多次，卖出之后有冷冻期
 * 
 * 只有一支股票
 * 股票只买卖多次
 * 有冷冻期
 * [买入，卖出，冷冻，买入。卖出]
 * 两次买入卖出之间有冷冻期
 */
public class Main5 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        /**
         * dp[i][0] 持有股票状态
         * dp[i][1] 持有现金状态，今天不能卖出股票！
         * dp[i][2] 卖出股票 ！
         * dp[i][3] 冷冻期状态
         */
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;// 先买再卖
        dp[0][3] = 0;// 冷冻期无法买卖

        for (int i = 1; i < n; i++) {
            //今天持有股票 昨天持有股票 昨天持有现金 昨天是冷冻期(买出》冷冻》买入)
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));
            //今天持有现金 昨天持有现金 昨天持有股票(持有》卖出)拆给了dp[i][2] 昨天是冷冻期(卖出》冷冻》持有)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            //今天卖出股票 昨天买入股票
            dp[i][2] = dp[i - 1][0] + prices[i];
            //今天进入冷冻期 昨天卖出股票 (卖出》冷冻)
            dp[i][3] = dp[i - 1][2];
        }

        // dp[n - 1][0] 持有股票一定不是最大收益
        return Math.max(dp[n - 1][1], Math.max(dp[n - 1][2], dp[n - 1][3]));
    }
}
