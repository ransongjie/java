package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * 买卖股票至多2次
 */
public class Main3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        /**
         * dp[i][0]无操作
         * dp[i][1]第1次买入
         * dp[i][2]第1次卖出
         * dp[i][3]第2次买入
         * dp[i][4]第2次卖出
         */
        int[][] dp = new int[n][5];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;// 当天买入卖出
        dp[0][3] = -prices[0];//买入卖出再买入
        dp[0][4] = 0;//买入卖出买入卖出

        for (int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        // 第2次卖出包含了第1次卖出，所以第2次卖出（持有现金）一定是最大值
        return dp[n - 1][4];
    }
}
