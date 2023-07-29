package com.xcrj.offer_special.pass2;

import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差
 * 给定一个24小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 */
public class Solution35 {
    /**
     * 鸽巢原理：如果要把n+1个物体放进n个盒子，那么至少有1个盒子包含2个或更多的物体。
     * - 共有24*60=1440种不同的分钟数，如果timePoints.size()>1440, 则一定存在相同的分钟数，相同的时间
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        // 鸽巢原理，存在相同时间
        if(timePoints.size()>24*60) return 0;

        // xcrj 按ASCII码排序时间字符串
        // 00:00(24:00) 00:01 00:50 01:00 ... 23:59
        Collections.sort(timePoints);

        int minMinutesDiff=Integer.MAX_VALUE;
        int minute0=getMinutes(timePoints.get(0));
        int minuteA=minute0;
        // 两两比较
        for(int i=1;i<timePoints.size();i++){
            int minuteB=getMinutes(timePoints.get(i));
            minMinutesDiff=Math.min(minMinutesDiff, Math.abs(minuteB-minuteA));
            // 准备下一次比较
            minuteA=minuteB;
        }
        // 首尾比较 xcrj 时间循环需要首尾比较 23点过后是0点 0点是24点
        minMinutesDiff=Math.min(minMinutesDiff,Math.abs(minute0+24*60-minuteA));
        return minMinutesDiff;
    }

    /**
     * 获取时间的分钟表示方式
     * @param timePoint
     * @return
     */
    public int getMinutes(String timePoint) {
        return ((timePoint.charAt(0)-'0')*10+(timePoint.charAt(1)-'0'))*60
        +(timePoint.charAt(3)-'0')*10
        +(timePoint.charAt(4)-'0');
    }
}
