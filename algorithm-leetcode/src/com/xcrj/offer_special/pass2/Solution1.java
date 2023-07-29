package com.xcrj.offer_special.pass2;
/**
 *  剑指 Offer II 001. 整数除法
 *  给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 *  整数除法的结果应当截去（truncate）其小数部分
 *  假设我们的环境只能存储 32 位有符号整数 数值范围是 [−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回 2^31 − 1
 */
class Solution1 {
    /**
     * 十进制：被除数减去了多少个除数
     * 二进制：被除数减去了多少个移动的除数
     */
    public int divide(int a, int b) {
        // a越界
        if (Integer.MIN_VALUE == a && -1 == b) {
            return Integer.MAX_VALUE;
        }

        // 记录结果符号
        boolean pNumbered = false;
        if (a < 0 && b < 0) {
            pNumbered = true;
        }
        if (a > 0 && b > 0) {
            pNumbered = true;
        }

        // 使用负数表示
        // xcrj long
        long ma = a < 0 ? a : -a;
        long mb = b < 0 ? b : -b;

        // 被除数<除数
        if (ma > mb) {
            return 0;
        }

        // 左移运算求解
        int r = 0;
        int shift = 31;
        // 被除数>=除数
        while (ma <= mb) {
            // 被除数还能再减一个除数吗
            // 被除数<除数
            while (ma > mb << shift) {
                shift--;
            }
            // 被除数减除数
            // 将被除数变小
            ma -= mb << shift;
            // 记录每次除数的移动
            // 1和mb同时左移相同位
            r += 1 << shift;
        }

        return pNumbered ? r : -r;
    }
}