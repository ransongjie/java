package com.xcrj.str;

/**
 * https://leetcode.cn/problems/repeated-substring-pattern/
 * 重复的子字符串
 * abab, 由ab重复两次构成
 * abcabcabc, 由abc重复三次构成
 */
public class Main6 {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        int n = s.length();
        int[] next = getNext(s);
        // 无部分匹配
        if (next[n - 1] == -1)
            return false;
        // next[n-1]+1, 长度为n字符串最长相等前后缀长度
        // n - (next[n - 1] + 1), 1个重复串的长度
        if (n % (n - (next[n - 1] + 1)) == 0)
            return true;
        return false;
    }
    /**
     * 获取部分匹配表
     *
     * @param s
     * @return 无部分匹配，next[all]=-1
     */
    private int[] getNext(String s) {
        char[] cs = s.toCharArray();
        // i, 长度为i+1的前缀串最后1个字符
        // j, 长度为i+1的后缀串最后1个字符
        int i = 0, j = -1, n = cs.length;
        int[] next = new int[n];
        // 长度为i+1的字符串，最长相等前后缀的长度为j+1
        next[i] = j;
        for (i = 1; i < n; i++) {
            // 无法全部匹配选择部分匹配
            while (j >= 0 && cs[i] != cs[j + 1]) {// j=0, 最多从头开始比较
                j = next[j];
            }
            // 全部匹配i和j统一后移
            if (cs[i] == cs[j + 1]) {
                j++;
            }
            //
            next[i] = j;
        }
        return next;
    }
}