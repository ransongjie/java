package com.xcrj.offer_special.pass4;

/**
 * 单词长度的最大乘积
 * 不含相同字符的单词长度的最大乘积
 */
public class Solution005 {
    public int maxProduct(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {//
                if (!hasSameChar(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    /**
     * 题目重点在于如何高效判断两个字符串是否含有相同字符
     *
     * @param s1
     * @param s2
     * @return true含有相同字符，false不含有相同字符
     */
    private boolean hasSameChar(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int a = 0, b = 0;
        for (char c :
                cs1) {
            a |= (1 << c - 'a');
        }
        for (char c :
                cs2) {
            b |= (1 << c - 'a');
        }
        //a和b只要有1个相同字符，则a&b>=1
        return (a & b) > 0;
    }
}
