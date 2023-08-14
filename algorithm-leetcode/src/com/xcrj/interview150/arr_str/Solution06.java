package com.xcrj.interview150.arr_str;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/h-index/solutions/869042/h-zhi-shu-by-leetcode-solution-fnhl
 * H指数
 * 至少有H篇文章被引用H次
 */
public class Solution06 {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = citations.length - 1;
        /**
         * 0篇论文，被引用量>=0
         * 1篇论文，被引用量>=1
         * ...
         * h篇论文，被引用量>=h
         */
        int h = 0;
        while (i >= 0 && citations[i] > h) {
            //h不能再增大了，从某个位置往后，h篇文章的被引用量>=h
            h++;
            i--;
        }
        return h;
    }

}
