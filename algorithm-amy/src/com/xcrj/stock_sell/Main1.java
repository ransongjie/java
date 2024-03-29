package com.xcrj.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * 1支股票只买卖1次》只有1只股票 && 股票只买卖1次(仅能买1次，仅能卖1次)
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Main1 {
    /**
     * 贪心策略：价格最低时买入，利润最大时卖出
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int lowPrice = Integer.MAX_VALUE;
        int highProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            lowPrice = Math.min(lowPrice, prices[i]);
            highProfit = Math.max(highProfit, prices[i] - lowPrice);
        }
        return highProfit;
    }

    public int maxProfit1(int[] prices) {
        //dp[i][0]=持有股票状态的最大收益，dp[i][1]=持有现金状态的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            //前1天持有股票dp[i-1][0]；前1天不持有股票（持有现金），又只能买1次
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);//1支股票只能买1次，卖1次
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    public int maxProfit2(int[] prices) {
        int[] dp = new int[2];
        dp[0] = -prices[0];//持有股票的最大收益
        dp[1] = 0;//持有现金的最大收益
        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];
    }
}
