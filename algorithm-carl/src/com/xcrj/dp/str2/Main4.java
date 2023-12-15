package com.xcrj.dp.str2;

/**
 * https://leetcode.cn/problems/edit-distance/
 * word1 变成 word2的最小代价
 * 编辑距离
 */
public class Main4 {
    public int minDistance(String word1, String word2) {
        return f(word1, word2, 1, 1, 1);
    }

    /**
     * s1变成s2
     * @param s1
     * @param s2
     * @param add 增加代价
     * @param del
     * @param rep 替换代价
     * @return
     */
    private static int f(String s1, String s2, int add, int del, int rep) {
        //dp[i][j]=长度为i的s1子数组 变成 长度为j的s2子数组 的最小代价
        //注意是s1 变成 s2，有方向性
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++) {
            dp[i][0] = i * del;
        }
        for (int j = 0; j < s2.length() + 1; j++) {
            dp[0][j] = j * add;
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                //替换则两个字符都不用考虑，s1删除s1[i-1]则不考虑第i-1个字符，s1添加了s2[j-1]则不考虑第j-1个字符
                dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ?
                        dp[i - 1][j - 1] : Math.min(dp[i - 1][j - 1] + rep, Math.min(del + dp[i - 1][j], add + dp[i][j - 1]));
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
