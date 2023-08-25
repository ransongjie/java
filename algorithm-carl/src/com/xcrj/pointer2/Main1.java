package com.xcrj.pointer2;

/**
 * 反转字符串
 * https://leetcode.cn/problems/reverse-string/
 */
public class Main1 {
    public void reverseString(char[] s) {
        int n = s.length, l = 0, r = n - 1;
        while (l < r) {
            // 异或交换
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l] ^= s[r];
            l++;
            r--;
        }
    }
}
