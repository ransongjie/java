package com.xcrj.interview150.heap;

import java.util.List;

/**
 * https://leetcode.cn/problems/ugly-number-ii
 * 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 */
public class Solution02 {
    /**
     * 多路归并 多指针
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        //返回第n个丑数
        int[] ans = new int[n + 1];
        //第1个丑数是1
        ans[1] = 1;
        int a, b, c, min;
        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx < ans.length; idx++) {
            //一次求出3个丑数，最小值索引++（类似二路归并），最小值赋值到ans[idx]
            a = ans[i2] * 2;
            b = ans[i3] * 3;
            c = ans[i5] * 5;
            min = Math.min(a, Math.min(b, c));
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            ans[idx] = min;
        }
        return ans[n];
    }
}
