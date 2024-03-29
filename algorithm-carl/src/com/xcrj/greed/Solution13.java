package com.xcrj.greed;

import java.util.Arrays;

/*
 * https://leetcode.cn/problems/non-overlapping-intervals/
 * 无重叠区间=总区间数量-重叠区间数量
 * 无重叠区间=总区间数量-射掉重叠区间的箭数
 * 认为[1,2]和[2,3]重叠
 */
public class Solution13{

    /*
     * 根据区间左边界排序
     * 遍历区间的左边界>=前面区间的最小右边界，count++
     * 遍历区间的左边界<前面区间的最小右边界，更新前面区间的最小右边界（包括新区间）
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b)-> Integer.compare(a[0],b[0]));
        int count = 1;
        for(int i = 1;i < intervals.length;i++){
            //重叠区间数量++
            if(intervals[i][0] >= intervals[i-1][1]){
                count++;
            }
            else{
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
            }    
        }
        return intervals.length - count;
    }
}