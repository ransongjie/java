package com.xcrj.interview150.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution1 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];

        // 按照左边界排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        //list结果区间最后一个元素的右边界 比较当前区间左边界
        for (int[] interval : intervals) {
            int nl = interval[0];
            int nr = interval[1];
            int lastr = list.get(list.size() - 1)[1];
            // [lastl,lastr,nl,nr]
            if (lastr < nl) {
                list.add(interval);
            }
            // [lastl,nl,lastr] max(lastr,nr)做右边界
            else {
                list.get(list.size() - 1)[1] = Math.max(lastr, nr);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
