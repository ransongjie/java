package com.xcrj.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
public class Main4 {
    // 将newInterval放到intervals最后，再合并区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //将newInterval放到intervals最后
        if (intervals.length == 0 && newInterval.length == 0) return new int[1][2];
        int[][] oldIntervals = intervals;
        intervals = new int[oldIntervals.length + 1][];
        int i = 0;
        for (; i < intervals.length - 1; i++) {//-1
            intervals[i] = oldIntervals[i];
        }
        intervals[i] = newInterval;
        //合并区间
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int j = 1; j < intervals.length; j++) {
            if (intervals[j][0] > right) {
                ans.add(new int[]{left, right});
                left = intervals[j][0];
                right = intervals[j][1];
            } else {
                right = Math.max(right, intervals[j][1]);
            }
        }
        ans.add(new int[]{left, right});
        return ans.toArray(new int[ans.size()][]);
    }
}
