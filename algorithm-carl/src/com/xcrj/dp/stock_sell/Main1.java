package com.xcrj.dp.stock_sell;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * 1支股票只买卖1次》只有1只股票 && 股票只买卖1次
 * 
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Main1 {
    // 贪心
    public int maxProfit(int[] prices) {
        int low = Integer.MAX_VALUE;
        int profit = 0;
        // 在向前遍历的过程中求最低价格下的最大利润
        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]);
            //在low基础上求最大利润
            profit = Math.max(profit, prices[i]-low);
        }
        return profit;
    }

    public int maxProfit1(int[] prices) {
        // 持有股票/现金 是一种状态。可以是之前达到的状态，也可以是今天达到的状态
        int[][] dp = new int[prices.length][2];// dp[i][0]第i天持有股票的最大收益 dp[i][1]第i天持有现金的最大收益
        dp[0][0] = -prices[0];//第0天持有股票的最大收益
        dp[0][1] = 0;//第0天持有现金的最大收益

        for (int i = 1; i < prices.length; i++) {
            // 第i天持有股票最大收益=max(前1天也持有股票,第i天刚买的股票)
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);// 只能买1次
            // 第i天持有现金最大收益=max(前1天也持有现金,第i天刚持有现金/卖出股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];//只买卖股票1次，最后1天持有现金的收益一定最大
    }

    public int maxProfit2(int[] prices) {
        //dp[0]持有股票的最大收益，dp[1]持有现金的最大收益
        int[] dp = new int[2];
        dp[0] = -prices[0];//第0天持有股票的最大收益
        dp[1] = 0;//第0天持有现金的最大收益
        for (int i = 1; i < prices.length; i++) {
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);// dp[0]=xxx放上面，导致dp[0]被优先更新
            dp[0] = Math.max(dp[0], -prices[i]);
        }
        return dp[1];
    }
}
