package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 爬楼梯的方法数
 */
public class Main1 {
    public int climbStairs(int n) {
        //dp数组
        int[] dp = new int[n + 1];
        //初始状态
        dp[0] = 1;//方法数，直接从第0台阶出发
        dp[1] = 1;//方法数，直接从第1台阶出发
        //状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];//两种情况，从第i-1台阶到第i台阶，从第i-2台阶到第i台阶
        }
        //结果
        return dp[n];
    }
}
