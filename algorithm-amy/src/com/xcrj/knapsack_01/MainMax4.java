package com.xcrj.knapsack_01;

/**
 * https://leetcode.cn/problems/ones-and-zeroes/
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 */
public class MainMax4 {
    public int findMaxForm(String[] strs, int m, int n) {
        /**
         * 问题转换
         * 重量 1的个数，0的个数
         * 价值 1的个数，0的个数
         * 背包 0的最大个数 m，1的最大个数 n
         * 背包最大承重 = 最多个数
         */
        //dp[i][j]=dp[最多i个0][最多j个1]最大子集的长度=满足最多i个0和最多j个1的字符串的个数
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            //统计0，1各自的数量
            int n0 = 0, n1 = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    n0++;
                } else {
                    n1++;
                }
            }

            //动态规划
            for (int i = m; i >= n0; i--) {
                for (int j = n; j >= n1; j--) {
                    //满足最多i个0和最多j个1的字符串的个数+1
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - n0][j - n1]);
                }
            }
        }

        return dp[m][n];
    }
}
