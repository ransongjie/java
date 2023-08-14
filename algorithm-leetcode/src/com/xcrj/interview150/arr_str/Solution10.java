package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/integer-to-roman
 * 整数转罗马数字
 */
public class Solution10 {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * num为0则找到结果
     * num能减最大值先减最大值，能减次大值先减次大值
     * 时间复杂度 O(1), values和symbols的长度都是有限的
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int n = values.length;
        //从大到小遍历每个值
        for (int i = 0; i < n; i++) {
            //先把最大值减完，再把次大值减完
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
            //num为0则找到结果
            if (num == 0) break;
        }
        return sb.toString();
    }
}
