package com.xcrj.dp.basic;

/**
 * https://leetcode.cn/problems/integer-break/submissions/
 * 整数拆分
 */
public class Main5 {
    public int integerBreak(int n) {
        // dp数组, dp[i]= i拆成多个数之和的最大乘积
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 0;// 只能拆成0+0, 0*0=0
        dp[1] = 0;// 只能拆成0+1, 0*1=0
        dp[2] = 1;// 只能拆成1+1, 1*1=1
        // 状态转移
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {// j=0，乘积一定为0
                // 拆成2个数相乘
                // 拆成多个数相乘
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        // 结果
        return dp[n];
    }
}
