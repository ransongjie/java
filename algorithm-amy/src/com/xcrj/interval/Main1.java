package com.xcrj.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 * 用最少的箭射爆所有气球
 * 贪心策略：将尽可能多的气球重叠在一起，多个气球的最小右边界内的气球只需要1箭
 */
public class Main1 {
    /*
     * 根据气球左边界排序
     * 前面气球的最小右边界<遍历气球的左边界，需要新的1箭
     * 前面气球的最小右边界>=遍历气球的左边界，尝试更新前面气球的最小右边界（包括新气球）
     */
    public int findMinArrowShots(int[][] points) {
        //根据左边界正序
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int ans = 1;//至少需要1箭
        for (int i = 1; i < points.length; i++) {//1
            if (points[i][0] > points[i - 1][1]) {
                ans++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return ans;
    }
}
