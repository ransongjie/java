package com.xcrj.str;

/**
 * https://leetcode.cn/problems/edit-distance/
 * word1 变成 word2的最小代价
 * 编辑距离
 */
public class MinDistance2 {
    public int minDistance(String word1, String word2) {
        return f(word1, word2, 1, 1, 1);
    }

    /**
     * s串变成t串所需最小代价
     * 注意，仅在s上操作
     *
     * @param s
     * @param t
     * @param add 增加1个字符的代价
     * @param del 删除1个字符的代价
     * @param rep 修改1个字符的代价
     * @return
     */
    private int f(String s, String t, int add, int del, int rep) {
        /**
         * dp[sLen][tLen]=s变成t的最小代价
         * dp[i][j]=s[0~i-1]变成t[0~j-1]的最小代价
         */
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = del * i;
        }
        for (int i = 0; i < t.length() + 1; i++) {
            dp[0][i] = add * i;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ?
                        dp[i - 1][j - 1] :
                        //将s[i-1]替换为t[j-1]，让s[0~i-1]=t[0~j-1]
                        //将s[i-1]删除，看s[0~i-2]=t[0~j-1]的代价
                        //将在s[0~i-1]后添加t[j-1]，看s[0~i-1]=t[0~j-2]的代价
                        Math.min(rep + dp[i - 1][j - 1], Math.min(del + dp[i - 1][j], add + dp[i][j - 1]));
            }
        }
        return dp[s.length()][t.length()];
    }
}
