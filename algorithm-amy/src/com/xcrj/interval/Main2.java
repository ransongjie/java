package com.xcrj.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
 * https://leetcode.cn/problems/non-overlapping-intervals/
 * 不重叠区间的数量
 * 无重叠区间=总区间数量-重叠区间数量
 * 无重叠区间=总区间数量-射掉重叠区间的箭数
 * 认为[1,2]和[2,3]不重叠
 */
public class Main2 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int cnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            //i区间左边界>=i-1区间的右边界
            if (intervals[i][0] >= intervals[i - 1][1]) {
                cnt++;
            } else {
                //更新前面重叠区间的最小右边界
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return intervals.length - cnt;
    }
}
