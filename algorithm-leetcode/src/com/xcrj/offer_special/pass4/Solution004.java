package com.xcrj.offer_special.pass4;

/**
 * 只出现一次的数字
 * 数字只出现1次和3次
 */
public class Solution004 {
    public int singleNumber(int[] nums) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            /**
             * 3^n+0/1=total
             */
            if (total % 3 == 1) {
                r |= (1 << i);
            }
        }
        return r;
    }
}
