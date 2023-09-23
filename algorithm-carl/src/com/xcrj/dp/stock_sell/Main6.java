package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 只有一支股票
 * 股票只买卖多次
 * 买入到卖出有1次手续费
 */
public class Main6 {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];// dp[i][0]第i天持有股票的最大收益 dp[i][1]第i天持有现金的最大收益
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 与买卖股票2的不同 - fee。在卖出的时候结算手续费
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
