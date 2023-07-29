package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 095. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class Solution95 {
    /**
     * 二维动态规划数组
     *
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * <p>
     * 两个字符串的最长公共子串，分阶段为 两个字符串的子串的最长公共子串
     * <p>
     * 使用dp[i][j]表示text1的长度为i(text1[0]~text1[i-1])的子串和text2的长度为j(text2[0]~text2[j-1])的子串的最长公共子序列的长度。
     * <p>
     * 当i=0或j=0时: dp[i][j]=0
     * - 空串(text1[0]到text1[i]是空串)和任何字符串的最长公共子序列的长度都是0
     * <p>
     * 当i>0&&j>0时
     * - dp[i][j]=dp[i−1][j−1]+1 if text1[i-1]=text2[j-1]
     * - dp[i][j]=max{dp[i−1][j],dp[i][j−1]}  if text1[i-1]!=text2[j-1]。求最长公共子序列，所以取最大值
     */
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * 使用dp[i][j]表示text1的长度为i(text1[0]~text1[i-1])的子串和text2的长度为j(text2[0]~text2[j-1])的子串的最长公共子序列的长度。
         * +1，长度为0的子串,...,长度为len的子串共(len+1)个子串
         * 当i=0或j=0时: dp[i][j]=0
         * 空串(text1[0]到text1[i]是空串)和任何字符串的最长公共子序列的长度都是0
         */
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // 当i>0&&j>0时
        // i=1,j=1。因为，当i=0或j=0时，dp[i][j]=0
        // =,因为dp[][]数组长度为len+1
        for (int i = 1; i <= text1.length(); i++) {
            char a = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char b = text2.charAt(j - 1);
                // dp[i][j]=dp[i−1][j−1]+1 if text1[i-1]=text2[j-1]
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // dp[i][j]=max{dp[i−1][j],dp[i][j−1]}  if text1[i-1]!=text2[j-1]
                // 求最长公共子序列，所以取最大值
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
