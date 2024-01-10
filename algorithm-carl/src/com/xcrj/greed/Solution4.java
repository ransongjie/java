package com.xcrj.greed;
/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 买卖股票II
 * 1支股票买卖多次》只有一支股票 && 股票买卖多次
 * 局部最优：收集每天的正利润，全局最优：求得最大利润
 */
public class Solution4{
    /**
     * 求最大利润，分解为求每天的正利润
     */
    public int maxProfit(int[] prices) {
        int ans=0;
        for(int i=1;i<prices.length;i++){
            ans+=Math.max(prices[i]-prices[i-1],0);
        }
        return ans;
    }
}