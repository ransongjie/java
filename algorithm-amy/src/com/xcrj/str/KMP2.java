package com.xcrj.str;

/**
 * https://leetcode.cn/problems/repeated-substring-pattern/
 * 重复字符串，字符串s是否由重复的子串构成
 * abab, 由ab重复两次构成
 * abcabcabc, 由abc重复三次构成
 */
public class KMP2 {
    /**
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int[] next = getNext(s);
        if (next[s.length() - 1] == -1) {//无部分匹配，所有字母都不同
            return false;
        }
        //n-(next[n-1]+1) = n-(最大部分匹配长度) = 1个重复子串的长度
        return s.length() % (s.length() - (next[s.length() - 1] + 1)) == 0;
    }

    private int[] getNext(String s) {
        int[] next = new int[s.length()];//next[部分匹配后缀子串末尾i]=部分匹配前缀子串末尾j
        int i = 0, j = -1;
        next[i] = j;
        for (i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
