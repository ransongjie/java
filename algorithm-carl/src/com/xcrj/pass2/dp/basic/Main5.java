package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/integer-break/submissions/
 * 整数拆分
 */
public class Main5 {
    public int integerBreak(int n) {
        // dp数组
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 0;//0+0, 0*0
        dp[1] = 0;//0+1, 0*1
        dp[2] = 1;//1+1, 1*1
        // 状态转换
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {// j+i-j=i, j*(i-j)
                // i拆成 j*(i-j)两数之和，i拆成 j*dp[i-j]多数之和
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        // 结果
        return dp[n];
    }
}
