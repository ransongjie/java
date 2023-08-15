package com.xcrj.interview150.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/insert-interval
 * 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 */
public class Solution2 {
    // 将newInterval放到intervals最后 再合并区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0 && newInterval.length == 0) return new int[0][2];

        // 将newInterval放到intervals最后
        int[][] intervals2 = new int[intervals.length + 1][];
        int i = 0;
        for (; i < intervals.length; i++) {
            intervals2[i] = intervals[i];
        }
        intervals2[i] = newInterval;
        intervals = intervals2;
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

    /**
     * 枚举
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0) return intervals;
        if (intervals.length == 0) return new int[][]{newInterval};
        List<int[]> res = new ArrayList<>();
        int L = newInterval[0], R = newInterval[1], len = intervals.length, j = 0;
        while (j < len && intervals[j][1] < L) res.add(intervals[j++]);      //重叠前直接加入List
        while (j < len && intervals[j][0] <= R) {     //处理重叠区域，L<=某区间r，开始处理重叠，L取min，R取max
            L = Math.min(L, intervals[j][0]);
            R = Math.max(R, intervals[j++][1]);
        }
        res.add(new int[]{L, R});
        while (j < len) res.add(intervals[j++]);     //不会再出现重叠区域，直接加入List
        return res.toArray(new int[res.size()][]);
    }

}
