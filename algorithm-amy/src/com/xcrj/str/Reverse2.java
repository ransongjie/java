package com.xcrj.str;

/**
 * 反转字符串II
 * https://leetcode.cn/problems/reverse-string-ii/
 * 每个2k，反转前k个字符
 */
public class Reverse2 {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        //控制步长
        for (int i = 0; i < cs.length; i += 2 * k) {
            int l = i;
            int r = Math.min(cs.length - 1, l + k - 1);
            while (l < r) {
                cs[l] ^= cs[r];
                cs[r] ^= cs[l];
                cs[l] ^= cs[r];
                l++;
                r--;
            }
        }
        return String.valueOf(cs);
    }
}
