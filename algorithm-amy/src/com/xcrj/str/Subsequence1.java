package com.xcrj.str;

/**
 * subsequence 子序列
 * https://leetcode.cn/problems/is-subsequence/
 * 判断子序列
 * s是不是t的子序列
 */
public class Subsequence1 {
    //子序列可以不连续，子串必须连续，空字符串不是子序列
    // s是不是t的子序列
    public boolean isSubsequence(String s, String t) {
        //dp[sLen][tLen]=t中s序列的长度，序列可不连续
        // dp[0~i-1子串a][0~j-1子串b]=b中a序列的长度
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < t.length() + 1; i++) {
            dp[0][i] = 0;//空串不是任何字符串的子序列
        }
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 0;//空串中空串子序列长度为0，空串中非空串子序列长度为0
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                //字符不相等，看0~j-2中i子序列的长度
                dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : dp[i][j - 1];
            }
        }
        //t中s子序列的长度=s的长度，则t中包含s序列
        return dp[s.length()][t.length()] == s.length();
    }
}
