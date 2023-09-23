package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 * 买卖股票至多k次
 */
public class Main4 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // dp[i][k][0]=持有现金/卖出股票的最大收益
        // dp[i][k][1]=持有股票的最大收益
        int[][][] dp = new int[n][k + 1][2];

        for (int i = 0; i < k + 1; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 偶数持有现金 dp[i][j][0] 依赖于 dp[i-1][j-1][1] 今天的A状态依赖于昨天的B状态
                // 第j次买入 第j次卖出
                // 先买入再卖出，dp[i][j][0]=第i天第j次卖出股票 需要 dp[i - 1][j][1]=第i-1天第j次卖入股票
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // 奇数持有股票
                // 第j-1次卖出 第j次买入
                // 先卖出再买入，dp[i][j][1]=第i天第j次买入股票 需要 dp[i - 1][j-1][1]=第i-1天第j-1次卖出股票
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][k][0];
    }
}
