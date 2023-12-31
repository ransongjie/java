package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 094. 最少回文分割
 * 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 */
public class Solution94 {
    /**
     * 动态规划
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * <p>
     * 将求[0..n]字符串分割为多个回文串的最小分割次数
     * <p>
     * 分阶段
     * - [0..0]子串分割为多个回文串的最小分割次数
     * - [0..1]子串分割为多个回文串的最小分割次数
     * - [0..i]子串分割为多个回文串的最小分割次数
     * - [0..n]子串分割为多个回文串的最小分割次数
     *
     * 动态规划数组：使用g(i,j)记录s[i...j]是否为回文串
     * 状态转移方程：
     * - 1个字符也是回文串
     * - 当前字符是否相等&&前一个子串是否为回文串
     *
     *
     * 动态规划数组：使用f[i]记录字符串的前缀s[0...i]的最少分割次数
     * 状态转移方程：
     * - [0..i]子串切割成回文串的最小切割次数=[0..j]子串切割成回文串的最小切割次数+1（[0..i]中最后1个回文串[j+1..i]）
     */
    public int minCut(String s) {
        //  使用g(i,j)记录s[i...j]是否为回文串
        boolean[][] g = new boolean[s.length()][s.length()];

        // 1个字符也是回文串
        for (boolean[] as : g) {
            Arrays.fill(as, true);
        }

        // 确定子串左边界后遍历右边界判子串是否为回文串
        // i往左
        for (int i = s.length() - 1; i >= 0; i--) {
            // j往右
            for (int j = i + 1; j < s.length(); j++) {
                // 当前字符是否相等&&前一个子串是否为回文串
                if (s.charAt(i) == s.charAt(j) && g[i + 1][j - 1]) g[i][j] = true;
                else g[i][j] = false;
            }
        }

        // 使用f[i]记录字符串的前缀s[0...i]的最少分割次数
        int[] f = new int[s.length()];

        // 求最少分割次数，设最大
        Arrays.fill(f, Integer.MAX_VALUE);

        // 确定子串右边界遍历左边界切割子串
        for (int i = 0; i < s.length(); i++) {
            // 整个子串是回文数，不需要分割，分割次数为0
            if (g[0][i]) f[i] = 0;
                // 整个子串不是回文数，从[1..i],[2..i],..,[i-1,i]分割子串
            else {
                for (int j = 0; j < i; j++) {
                    // j+1,因为[0..i]已经判断过了
                    if (g[j + 1][i]) {
                        // [0..i]子串切割成回文串的最小切割次数=[0..j]子串切割成回文串的最小切割次数+1（[0..i]中最后1个回文串[j+1..i]）
                        // +1,因为[j+1..i]子串也是回文数，f[j]表示[0..i]子串分割为回文串的最小分割次数
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[s.length() - 1];
    }
}
