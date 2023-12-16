package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 1支股票买卖多次》只有一支股票 && 股票买卖多次
 */
public class Main2 {
    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][2];// dp[i][0]第i天持有股票的最大收益 dp[i][1]第i天持有现金的最大收益
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            // 与买卖股票1的唯一不同 dp[i - 1][1] - prices[i]
            // 第i天持有股票的最大收益=第i天无操作 看第i-1天持有股票，第i天买入股票 第i-1天持有现金
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 第i天持有现金的最大收益=第i天无操作 看第i-1天持有现金，第i天卖出股票 第i-1天持有股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }
}
