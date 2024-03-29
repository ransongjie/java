package com.xcrj.palindrome;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * 最长回文子串
 * Manacher
 */
public class Main6 {
    public String longestPalindrome(String s) {
        s = convertStr(s);
        int[] maxRC = manacher(s);
        int maxR = maxRC[0];
        int maxC = maxRC[1];
        //结果
        StringBuilder sb = new StringBuilder();
        for (int i = (maxC - maxR); i < (maxC + maxR); i++) {
            sb.append(s.charAt(i) == '#' ? "" : s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * @param s 转换后的字符串
     * @return int[0] = maxR-1 = 转换后字符串的最大回文半径 = 原字符串的最大回文子串的长度
     * int[1] = maxC = 转换后字符串的最大回文半径的回文中心
     */
    private int[] manacher(String s) {
        int maxR = 0, maxC = 0;
        int[] rs = new int[s.length()];//s[i]的最大回文半径
        for (int i = 0; i < s.length(); i++) {
            /**
             * i在边界外
             * i在边界上
             * i在边界内
             */
            rs[i] = i >= (maxC + maxR) ? 1 : Math.min(rs[maxC - (i - maxC)], maxC + maxR - i);
            while (i + rs[i] < s.length() && i - rs[i] >= 0 && s.charAt(i + rs[i]) == s.charAt(i - rs[i])) {
                ++rs[i];
            }
            if (rs[i] > maxR) {
                maxR = rs[i];
                maxC = i;
            }
        }
        return new int[]{maxR - 1, maxC};//maxR
    }

    //添加#，将字符串变成偶数长度
    private String convertStr(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        return sb.toString();
    }
}
