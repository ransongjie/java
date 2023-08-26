package com.xcrj.dp.basic;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 * 斐波那契数
 */
public class Main0 {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        // dp数组
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 0;
        dp[1] = 1;
        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 结果
        return dp[n];
    }
}
