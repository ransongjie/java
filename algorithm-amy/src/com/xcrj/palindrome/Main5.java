package com.xcrj.palindrome;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/palindrome-partitioning-ii/description/
 * 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。返回符合要求的最少分割次数
 */
public class Main5 {
    public int minCut(String s) {
        //dp[i][j]=s[i]~s[j]子串是否回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (boolean[] as : dp) {
            Arrays.fill(as, true);
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j) ? true : false;
            }
        }

        //fs[i]=0~i子串的最少切割次数
        int[] fs = new int[s.length()];
        Arrays.fill(fs, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (dp[0][i]) {
                fs[i] = 0;
            } else {
                //0~i子串不是回文串，需要在0~i中间切割
                for (int j = 0; j < i; j++) {
                    //在s[j]位置切割为 0~j子串 和 j+1~i子串
                    if (dp[j + 1][i]) {
                        fs[i] = Math.min(fs[i], fs[j] + 1);
                    }
                }
            }
        }

        return fs[s.length() - 1];
    }
}
