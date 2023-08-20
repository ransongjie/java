package com.xcrj.interview150.math;

/**
 * https://leetcode.cn/problems/factorial-trailing-zeroes
 * 阶乘后的零
 */
public class Solution01 {
    /**
     * n! 尾零的数量即为 n! 中因子 10 的个数，而 10=2×5 ，因此转换成求 n! 中 质因子 2 的个数和质因子 5 的个数的 较小值。
     * 由于质因子 5 的个数不会大于质因子 2 的个数，我们可以仅考虑质因子 5 的个数。
     * ！！！1~n之中有多少个5的因数
     * <p>
     * 1~n中 p的倍数 有n/p 个
     * 1~n中 p^2的倍数 有n/p^2 个
     */
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            /**
             * 第1次除5得到26，表明存在26个包含 1个因数5的数
             * 第2次除5得到5，表明存在5个包含2个因数5的数(这些数字的1个因数5已经在第1次运算的时候统计了)
             * 第3次除5得到1，表明存在1个包含3个因数5的数(这些数字的2个因数5已经在前2次运算的时候统计了)
             */
            n /= 5;
            res += n;
        }
        return res;
    }
}
