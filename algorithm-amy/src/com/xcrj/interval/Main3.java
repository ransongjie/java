package com.xcrj.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * https://leetcode.cn/problems/merge-intervals/
 * 合并重叠区间
 * 认为[1,2]和[2,3]重叠
 */
public class Main3 {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //i区间的左边界>前面区间的右边界
            if (intervals[i][0] > right) {
                ans.add(new int[]{left, right});
                //新的区间
                left = intervals[i][0];
                right = intervals[i][1];
            } else {
                //扩大右边界
                right = Math.max(right, intervals[i][1]);
            }
        }
        ans.add(new int[]{left, right});
        return ans.toArray(new int[ans.size()][]);
    }
}
