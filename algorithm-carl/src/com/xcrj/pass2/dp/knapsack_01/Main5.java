package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/ones-and-zeroes/
 * 474. 一和零
 */
public class Main5 {
    /**
     * 转换为01背包问题
     * 物品=字符串
     * bagSize=
     * weight=0的个数和1的个数
     * value=weight
     * @param strs
     * @param m    0的数量
     * @param n    1的数量
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // dp数组
        // dp[i][j]=含有i个0和j个1的最大集合（含有字符串的数量最多）
        int[][] dp = new int[m + 1][n + 1];

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
