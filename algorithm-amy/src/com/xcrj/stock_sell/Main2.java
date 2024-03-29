package com.xcrj.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 1支股票买卖多次》只有一支股票 && 股票买卖多次
 */
public class Main2 {
    public int maxProfit(int[] prices) {
        //dp[i][0]=持有股票的最大收益，dp[i][1]=持有现金的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }
}
