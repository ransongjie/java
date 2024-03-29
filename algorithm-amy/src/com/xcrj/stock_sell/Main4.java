package com.xcrj.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 * 1支股票&&至多买卖k次 》先买入再卖出
 */
public class Main4 {
    public int maxProfit(int k, int[] prices) {
        //k+1, k=0, 无操作状态
        //dp[i][k][0]=第k次持有股票状态的最大收益
        //dp[i][k][1]=第k次持有现金状态的最大收益
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[0][i][0] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < k + 1; j++) {//j=1，j=0是无操作状态
                /**
                 * 状态之间转换次序：先持有股票，再持有现金; 先有第j-1次，再有第j次
                 * j-1次持有股票，j-1次持有现金
                 * j次持有股票，j持有现金
                 */
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + prices[i]);
            }
        }
        return dp[prices.length - 1][k][1];
    }
}
