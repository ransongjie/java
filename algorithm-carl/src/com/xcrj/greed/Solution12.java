package com.xcrj.greed;

import java.util.Arrays;

/*
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 * 贪心策略：将尽可能多的气球重叠在一起，多个气球的最小右边界内的气球只需要1箭
 */
public class Solution12{
    /*
     * 根据气球左边界排序
     * 前面气球的最小右边界<遍历气球的左边界，需要新的1箭
     * 前面气球的最小右边界>=遍历气球的左边界，更新前面气球的最小右边界（包括新气球）
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a, b)->Integer.compare(a[0], b[0]));// Integer内置比较方法，防止溢出
        // 至少需要1箭
        int ans=1;
        for(int i=1;i<points.length;i++){
            // 第i个气球的左边界>前面气球的最小右边界
            if(points[i][0]>points[i-1][1]){
                ans++;
            }
            // 纳入第i个气球，更新前面气球的最小右边界
            else{
                points[i][1]=Math.min(points[i-1][1],points[i][1]);// points[i][1]
            }
        }
        return ans;
    }
}