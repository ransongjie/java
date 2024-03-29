package com.xcrj.str;

/**
 * 反转字符串
 * https://leetcode.cn/problems/reverse-string/
 */
public class Reverse1 {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l] ^= s[r];
            l++;
            r--;
        }
    }
}
