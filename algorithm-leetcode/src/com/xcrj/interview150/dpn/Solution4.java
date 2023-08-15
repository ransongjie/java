package com.xcrj.interview150.dpn;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class Solution4 {
    //Manacher算法
    public String longestPalindrome(String str) {
        if (str == null || str.length() == 0) return "";

        //cstr 奇数长度
        String cstr = convert(str);
        char[] cs = cstr.toCharArray();
        //每个字符的回文半径
        int[] rs = new int[cstr.length()];
        int maxR = 0;//历史最大回文半径
        int maxC = 0;//历史最大回文半径的回文中心
        for (int i = 0; i < cstr.length(); i++) {
            //1
            rs[i] = i >= (maxC + maxR) ? 1 : Math.min((maxC + maxR - i), rs[maxC - (i - maxC)]);
            while (i + rs[i] < cs.length && i - rs[i] > -1 && cs[i - rs[i]] == cs[i + rs[i]]) {
                ++rs[i];
            }

            if (rs[i] > maxR) {
                maxR = rs[i];
                maxC = i;
            }
        }
        //回文直径中有#，回文直径是奇数多1个#
        StringBuilder sb = new StringBuilder();
        for (int i = maxC - (maxR - 1), j = 0; i <= maxC + (maxR - 1); i++, j++) {
            //j为奇数才append
            if ((j & 1) == 1) sb.append(cstr.charAt(i));
        }
        return sb.toString();
    }

    private static String convert(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        char[] cs = str.toCharArray();
        for (char c : cs) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }
}
