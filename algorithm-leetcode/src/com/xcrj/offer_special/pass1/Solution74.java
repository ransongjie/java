package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 剑指 Offer II 074. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution74 {
    /**
     * 按左边界排序
     * 放入list中
     * - 若新结点左端点>list[size-1]的右端点，则直接放入list中
     * - 若新结点左端点<=list[size-1]的右端点，则比较两个结点的右端点取最大值
     */
    public int[][] merge(int[][] intervals) {
        // 特殊情况处理，第0行有2列
        if (0 == intervals.length) return new int[0][2];
        // 按左边界排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 放入list中
        List<int[]> list = new ArrayList<>(intervals.length);
        for (int[] interval : intervals) {
            if (list.isEmpty()) {
                list.add(interval);
                continue;
            }
            int nl = interval[0], nr = interval[1];
            int or = list.get(list.size() - 1)[1];
            if (or < nl) {
                list.add(interval);
                continue;
            }
            if (or >= nl) {
                list.get(list.size() - 1)[1] = Math.max(or, nr);
                continue;
            }
        }
        // ！指定输出数组
        return list.toArray(new int[list.size()][]);
    }
}
