package com.xcrj.company.pass1;

import java.util.Arrays;

public class A072MinKthPairDiffAbs {
    /**
     * 暴力 O(n*n)
     *
     * @param as
     * @return
     */
    public static int minKthPairDiffAbs(int[] as, int k) {
        int n = as.length;
        //第1个数和后面n-1个数组成数对，第2个数和后面n-2个数组成数对
        int m = ((n - 1) * n) >> 1;
        if (n < 2 || k < 1 || k > m) return -1;
        //第1个数和后面n-1个数组成数对，第2个数和后面n-2个数组成数对
        int[] ans = new int[m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans[idx++] = Math.abs(as[i] - as[j]);
            }
        }
        Arrays.sort(ans);
        //k从1开始，数组从0开始
        return ans[k - 1];
    }

    /**
     * O(logn)：大流程，二分，求最后一个数字对差值绝对值<=a的数字对个数<need的数字对差值绝对值a+1
     * O(n)：小流程，双指针不回退，求数字对绝对值差值<=x的数字对个数
     * O(logn*n)
     *
     * @param as
     * @param k
     * @return
     */
    public static int minKthPairDiffAbs2(int[] as, int k) {
        int n = as.length;
        if (n < 2 || k < 1 || k > ((n * (n - 1)) >> 1)) {
            return -1;
        }
        //
        Arrays.sort(as);
        int minAbsDiff = 0, maxAbsDiff = as[n - 1] - as[0];
        //rightest=-1: 若as[]中所有元素相等为x，数字对差值都为0，数字对差值第k小差值应该是0
        int l = minAbsDiff, r = maxAbsDiff, m = -1, rightest = -1;
        while (l <= r) {
            m = ((l + r) >> 1);
            //数字对差值绝对值<m的数字对个数<k
            if (valid(as, m, k)) {
                //一直记录满足要求的值，直到最后一个满足条件的值
                rightest = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return rightest + 1;
    }

    /**
     * 双指针不回退
     * 统计 数字对差值绝对值 <=adiff的数字对个数 m
     *
     * @param as
     * @param adiff 数字对差值绝对值<=adiff
     * @param k     的个数 <k
     * @return m<k返回true, 否则false
     */
    private static boolean valid(int[] as, int adiff, int k) {
        int ans = 0;
        //r=1，数字对不是同一个数
        //r=Math.max(r,l++)，r>=l
        for (int l = 0, r = 1; l < as.length; r = Math.max(r, l++)) {
            while (r < as.length && Math.abs(as[r] - as[l]) <= adiff) {
                r++;
            }
            ans += r - l - 1;
        }
        return ans < k;
    }
}
