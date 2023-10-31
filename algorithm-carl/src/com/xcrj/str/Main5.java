package com.xcrj.str;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 找出字符串中第一个匹配项的下标
 */
public class Main5 {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        char[] hs = haystack.toCharArray();
        char[] ns = needle.toCharArray();
        int[] next = getNext(needle);
        int i = 0, j = -1, n = haystack.length(), m = needle.length();
        for (i = 0; i < n; i++) {// i=0，因为i=0没有求过
            // 无法全部匹配，选择部分匹配
            while (j >= 0 && hs[i] != ns[j + 1]) {
                j = next[j];
            }
            // 全部匹配，i和j都后移
            if (hs[i] == ns[j + 1]) {
                j++;
            }
            // 完全匹配
            if (j == m - 1) {// 因为上面if比较的是j+1，最后1个字符也相等
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * 部分匹配表
     * 长度为 0 的子串，前后缀匹配长度=-1
     * 长度为 1 的子串，前后缀匹配长度=-1
     * 长度为 i 的子串，前后缀匹配长度 最大为 i-1
     *
     * 获取部分匹配表
     * next[i]=j, 长度为i+1的字符串，最长相等前后缀字符串长度j+1，的部分匹配位置j
     *
     * @param needle
     * @return 无部分匹配，next[all]=-1
     */
    public int[] getNext(String needle) {
        char[] cs = needle.toCharArray();
        // i, 长度为i+1的前缀串最后1个字符
        // j, 长度为j+1的后缀串最后1个字符
        int i = 0, j = -1, n = needle.length();
        int[] next = new int[n];
        // 长度为0+1字符串，最长相等前后缀字符串长度
        next[i] = j;
        // 长度为i+1字符串，最长相等前后缀字符串长度
        for (i = 1; i < n; i++) {// i=1，因为i=0已经求过了
            // 前后缀末尾字符不相等
            while (j >= 0 && cs[i] != cs[j + 1]) {// j=0，最多从头开始比较
                j = next[j];// 部分匹配，继续比较
            }
            // 后缀末尾字符==前缀末尾字符
            if (cs[i] == cs[j + 1]) {
                j++;// 前后缀字符串都+1
            }
            // 更新部分匹配表
            next[i] = j;
        }
        return next;
    }
}