package com.xcrj.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * 1支股票&&至多买卖2次 》先买入再卖出
 */
public class Main3 {
    /**
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /**
         * 状态之间转换 先持有股票，再持有现金; 先有第1次，再有第2次
         * dp[i][0]=无操作状态的最大收益
         * dp[i][1]=第1次持有股票状态的最大收益
         * dp[i][2]=第1次持有现金状态的最大收益
         * dp[i][3]=第2次持有股票状态的最大收益
         * dp[i][4]=第2次持有现金状态的最大收益
         */
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < prices.length; i++) {
            //max(之前也是第1次持有股票的状态,之前不是第1次持有股票状态（之前是无操作的状态）)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            //max(之前也是第1次持有现金的状态，之前不是第1次持有现金的状态（之前是第1次持有股票的状态）)
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }
}
