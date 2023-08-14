package com.xcrj.interview150.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence
 * 时间复杂度 O(n)
 * 最长连续序列
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Solution24 {
    /**
     * hash加速
     * 不存在更小的数才进入内层循环
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        //存入
        Set<Integer> set = new HashSet<>();
        for (int n :
                nums) {
            set.add(n);
        }
        //遍历
        int res = 0;
        int len;
        for (int n :
                nums) {
            //nums中的最小值才会执行1次内层循环
            if (!set.contains(n - 1)) {
                //统计
                len = 1;
                while (set.contains(n + len)) {
                    len++;
                }
                res = Math.max(res, len);
            }

        }

        return res;
    }
}
