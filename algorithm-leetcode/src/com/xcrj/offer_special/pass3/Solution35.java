package com.xcrj.offer_special.pass3;

import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 035. 24小时制最小时间差
 * 24小时制（"HH:MM"）的时间列表
 * 以分钟数表示的 最小时间差
 */
public class Solution35 {
    /**
     * 鸽巢原理：
     * - 如果要把n+1个物体放进n个盒子，那么至少有1个盒子包含2个或更多的物体。
     * - 共有24*60=1440种不同的分钟数，如果timePoints.size()>1440, 则一定存在相同的分钟数，相同的时间
     * 
     * 排序后
     * - 从左到右两两比较，求最小时间差
     * - 首尾比较，求最小时间差
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int minDiff=Integer.MAX_VALUE;
        //鸽巢原理
        if(timePoints.size()>24*60) return 0;
        //
        Collections.sort(timePoints);
        //
        int m0=getMinutes(timePoints.get(0));
        int ma=m0;
        for(int i=1;i<timePoints.size();i++){
            int mb=getMinutes(timePoints.get(i));
            int diff=Math.abs(mb-ma);
            minDiff=Math.min(minDiff, diff);
            ma=mb;
        }
        // xcrj 00:12 = 24:12
        minDiff=Math.min(minDiff,m0+24*60-ma);
        //
        return minDiff;
    }

    /**
     * 获取时间的分钟表示方式
     * @param timePoint
     * @return
     */
    public int getMinutes(String timePoint) {
        // xcrj 12:23, 中间的:需要跳过
        // hour
        int mh=((timePoint.charAt(0)-'0')*10+(timePoint.charAt(1)-'0'))*60;
        // minute
        int mm=((timePoint.charAt(3)-'0')*10+(timePoint.charAt(4)-'0'));

        return mh+mm;
    }
}
