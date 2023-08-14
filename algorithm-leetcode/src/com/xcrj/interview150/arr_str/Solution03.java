package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 */
public class Solution03 {
    /**
     * 贪心
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                r += prices[i] - prices[i - 1];
            }
        }

        return r;
    }

    /**
     * 暴力递归
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        f(prices, 0, 0, 0);
        return res;
    }

    private int res = 0;

    /**
     * @param nums
     * @param idx
     * @param status 当前的操作依赖于之前的操作 0 不持有 1 持有
     * @param profit 利润
     * @return
     */
    private void f(int[] nums, int idx, int status, int profit) {
        if (idx == nums.length) {
            res = Math.max(profit, res);
            return;
        }
        //不操作
        f(nums, idx + 1, status, profit);
        //不持有时买入
        if (status == 0) {
            f(nums, idx + 1, 1, profit - nums[idx]);
        }
        //持有时买出
        else {
            f(nums, idx + 1, 0, profit + nums[idx]);
        }
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        /**
         * 定义状态数组
         * dp[i][j]=从0到第i天，j状态对待股票i，获得的收益
         * j=0，不持有股票
         * j=1, 持有股票
         */
        int[][] dp = new int[n][2];

        /**
         * 初始值
         */
        dp[0][0] = 0;//第0天不买入股票
        dp[0][1] = -prices[0];//第0天买入股票

        /**
         * 状态转移
         */
        for (int i = 1; i < n; i++) {
            //当天没持有股票，前一天也没持有股票，前一天持有股票+以今天的价格卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        /**
         * 结果
         * dp[n-1][0]=从0到最后一天，不持有股票，获得的收益
         */
        return dp[n - 1][0];
    }

    /**
     * 滚动数组优化动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int cash = 0, stock = -prices[0], preCash;

        for (int i = 1; i < n; i++) {
            preCash = cash;
            cash = Math.max(cash, stock + prices[i]);
            stock = Math.max(stock, preCash - prices[i]);
        }
        return cash;
    }
}
