package com.xcrj.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 1支股票&&买卖多次，卖出之后有冷冻期
 * [买入，卖出，冷冻，买入。卖出]
 */
public class Main5 {
    public int maxProfit(int[] prices) {
        /**
         * dp[i][0]=持有股票
         * dp[i][1]=不卖出股票, 持有现金
         * dp[i][2]=卖出股票, 持有现金
         * dp[i][3]=卖出股票持有现金的冷冻期
         */
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        for (int i = 1; i < prices.length; i++) {
            //前1天就持有股票，前1天持有现金（不能卖出股票），前1天是冷冻期
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));
            //前1天就持有现金（没卖出股票），前1天持有股票 今天卖出股票 错误 不能卖出股票
//            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            //前1天持有现金（不能卖出股票）, 前1天是卖出股票持有现金的冷冻期
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            //持有现金（必须卖出股票）《 持有股票，再卖出股票
            dp[i][2] = dp[i - 1][0] + prices[i];
            //进入冷冻期，必须卖出股票
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(dp[prices.length - 1][1], Math.max(dp[prices.length - 1][2], dp[prices.length - 1][3]));
    }
}
