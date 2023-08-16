package com.xcrj.interview150.bit;

/**
 * https://leetcode.cn/problems/bitwise-and-of-numbers-range
 * 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * <p>
 * 输入：left = 5, right = 7
 * 输出：4
 */
public class Solution02 {
    /**
     * [left,right]的数字按位与 求最长公共前缀 后缀中只有有1个0，则为0
     * 等价于求 left 和 right的 最长公共前缀
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    /**
     * Brian Kernighan (布莱恩·柯林汉) 算法 丢弃 right最右边的1 直到 = left, 这样也是求了最长公共前缀
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd2(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
