package com.xcrj.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-partitioning/description/
 * 分割回文子字符串
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 */
public class Main4 {
    public List<List<String>> partition(String s) {
        boolean[][] dp = f(s);
        g(dp, s, 0);
        return lss;
    }

    //获取动态规划数字，子串是否回文串，子串回文串
    private boolean[][] f(String s) {
        //dp[i][j]=s[i]到s[j]的子串，是否回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        //单个字母是回文串，对角线是回文串
//        for (int i = 0; i < s.length(); i++) {
//            dp[i][i] = true;
//        }
        //对角线是回文串，i~j是回文串，j~i也是回文串，对称阵。
        //干脆直接都假设为回文串，候选将不是回文串的设置为false
        for (boolean[] fs : dp) Arrays.fill(fs, true);
        /**
         * i左边界，j右边界
         * 这个三角形是所有子串
         * n-1~n
         * n-2~n-1 n-2~n
         * n-3~n-2 n-3~n-1 n-3~n
         * ...
         * 0~1 0~2 ... 0~n
         */
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                //s[i+1]~s[j-1]是否回文串
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j) ? true : false;
            }
        }
        return dp;
    }

    /**
     * 获取结果链表，就是遍历dp
     * 0~0 0~1 0~2 ... 0~n-1
     * 1~1 1~2 ... 1~n-1
     * ...
     * n-1~n-1
     */
    List<List<String>> lss = new ArrayList<>();
    List<String> ls = new ArrayList<>();

    private void g(boolean[][] dp, String s, int i) {
        if (i == s.length()) {
            lss.add(new ArrayList<>(ls));//new ArrayList()
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                ls.add(s.substring(i, j + 1));
                g(dp, s, j + 1);
                ls.remove(ls.size() - 1);
            }
        }
    }
}
