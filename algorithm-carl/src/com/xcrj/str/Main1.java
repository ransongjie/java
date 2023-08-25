package com.xcrj.str;

/**
 * 反转字符串II
 * https://leetcode.cn/problems/reverse-string-ii/
 */
public class Main1 {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, i = 0, l, r;
        char tmp;
        for (; i < n; i += 2 * k) {//每个2k，反转前k个字符
            // 反转起始位置
            l = i;
            r = Math.min(n - 1, l + k - 1);
            // 反转
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
