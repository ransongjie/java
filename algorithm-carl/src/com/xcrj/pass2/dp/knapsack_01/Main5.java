package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/ones-and-zeroes/
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 最大子集=子集中元素最多
 */
public class Main5 {
    /**
     * 二维01背包问题
     * 转换为01背包问题
     * 物品=字符串
     * bagSize=[0字符的数量m+1][1字符的数量+1]
     * weight=0的个数，1的个数
     * value=weight
     * @param strs
     * @param m    0的数量
     * @param n    1的数量
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // dp数组
        // dp[i][j]=含有i个0和j个1的最大集合（这个集合含有最多的字符串，集合中字符串的数量最多）
        int[][] dp = new int[m + 1][n + 1];

        /**
         * 问题转换 二维背包
         * bagSize=0的个数, 1的个数
         * weight=strs
         * value=num0, num1
         */
        int num0, num1;
        for (String str : strs) {
            // 统计str中0的数量，1的数量
            num0 = 0;
            num1 = 0;
            for (char c : str.toCharArray()) {
                if (c == '0')
                    num0++;
                else
                    num1++;
            }

            // 状态转移
            for (int i = m; i >= num0; i--) {
                for (int j = n; j >= num1; j--) {
                    // 不要这个单词，要这个单词
                    // str这个单词提供了num0个0，num1个1。求i个0和j个1，看dp[i-num0][j-num1]
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - num0][j - num1]);
                }
            }
        }

        // 结果
        return dp[m][n];
    }
}
