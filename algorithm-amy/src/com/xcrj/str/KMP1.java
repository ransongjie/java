package com.xcrj.str;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 找出字符串中第一个匹配项的下标
 * KMP适用于两个字符串之间的比较
 */
public class KMP1 {
    //str1 cmp str2
    public int strStr(String haystack, String needle) {
        int[] next = getNext(needle);
        //从0，-1开始
        int i = 0, j = -1;//都是下标 i=haystack idx, j=needle idx
        for (; i < haystack.length(); i++) {
            //不能全部匹配，选择部分匹配
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            //匹配
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            //结束
            if (j == needle.length() - 1) {
                return i - (needle.length() - 1);//第一个匹配项的下标
            }
        }
        return -1;
    }

    /**
     * prefix cmp suffix
     * 获取部分匹配表，字符串的最长部分匹配长度，前后缀字串相等的最长长度
     * 前后缀字串不是对称比较
     * 长度为 0 的子串，部分匹配长度=-1
     * 长度为 1 的子串，部分匹配长度=0
     * 长度为 i 的子串，部分匹配长度可能最大=i-1
     * next[i]=j, 长度为i+1的子串，部分匹配位置为j，部分匹配长度=j+1；后缀尾字符i，前缀尾字符j
     *
     * @param s
     * @return
     */
    private int[] getNext(String s) {
        //next[i]=j, 部分匹配位置为j
        int[] next = new int[s.length()];
        next[0] = -1;//长度为 1 的子串，匹配位置=-1，部分匹配长度=0
        //从1，-1开始
        int i = 1, j = -1;
        for (; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                //next[i]不完全匹配，使用next[j]更新前缀尾部字符j，进行部分匹配
                j = next[j];
            }
            //后缀尾字符i == 前缀尾字符j+1
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;//更新匹配位置
            }
            next[i] = j;//长度为i+1的子串，部分匹配位置为j，部分匹配长度=j+1
        }
        return next;
    }
}
