package com.xcrj.interview150.bit;

/**
 * https://leetcode.cn/problems/single-number-ii
 * 只出现一次的数字 II
 */
public class Solution01 {
    /**
     * 求每一位的值
     * %3
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0, total;

        for (int i = 0; i < 32; i++) {
            total = 0;
            for (int num :
                    nums) {
                //每一位是0还是1
                total += (num >> i) & 1;
            }
            //第i位 只出现1次的num为1
            if (total % 3 != 0) {
                ans |= 1 << i;
            }
        }

        return ans;
    }
}
