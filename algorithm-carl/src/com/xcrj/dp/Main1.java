package com.xcrj.dp;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 爬楼梯的方法数
 */
public class Main1 {
    public int climbStairs(int n) {
        // dp数组
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 1;//只有1种方法到0
        dp[1] = 1;//只有1种方法到1
        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];// 从第i-1台阶而来，从第i-2台阶而来
        }
        // 结果
        return dp[n];
    }
}
