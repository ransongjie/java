package com.xcrj.interview150.binary_search;

/**
 * 实数的立方根 开三次方
 */
public class Solution07 {
    public double myCube(int x) {
        double l = -x, r = x;
        while (r - l > 1e-8) {
            double mid = (l + r) / 2;
            if (mid * mid * mid < x) l = mid;
            else r = mid;
        }
        return l;
    }
}
