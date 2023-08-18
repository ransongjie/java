package com.xcrj.interview150.binary_search;

/**
 * https://leetcode.cn/problems/find-peak-element/
 * 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 题目严格保证了 nums[i]!=nums[i-1]
 * <p>
 * 求局部最大值
 */
public class Solution02 {
    /**
     * @param as
     * @return 局部最大值的索引
     */
    public int findPeakElement(int[] as) {
        // 异常
        if (as == null || as.length < 1) {
            return -1;
        }
        // 边界情况
        if (as.length == 1) {
            return 0;
        }
        if (as[0] > as[1]) {
            return 0;
        }
        int l = as.length - 1;
        if (as[l] > as[l - 1]) {
            return l;
        }
        //一般情况
        return f(as, 0, l);
    }

    public static int f(int[] as, int start, int end) {

        int mid = (start + end) / 2;
        if (as[mid] > as[mid + 1] && as[mid] > as[mid - 1]) {
            return mid;
        }

        //任意两个元素值不等 所以只有这两种情况 1个数大1个数小
        if (as[mid] < as[mid + 1]) {
            return f(as, mid + 1, end);
        } else {
            return f(as, start, mid - 1);
        }
    }

    /**
     * 求全局最大值的索引
     * @param as
     * @return
     */
    public int findPeakElement2(int[] as) {
        int maxIdx=0;
        for (int i = 1; i < as.length; i++) {
            if(as[maxIdx]<as[i]){
                maxIdx=i;
            }
        }
        return maxIdx;
    }
}
