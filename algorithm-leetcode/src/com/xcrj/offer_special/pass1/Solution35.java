package com.xcrj.offer_special.pass1;

import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 */
public class Solution35 {

    /**
     * ！！！java字符串排序规则
     * 1. 首个不相等字符的ASCII码
     * 2. ASCII码相同再比较字符长度，可认为空白字符ASCII码<其他字符的ASCII码
     * <p>
     * 直接对字符串排序代替对数字排序（要求字符串长度相同&&ASCII大小关系同于数字大小关系）
     * ！！！先直接对时间字符串排序（为什么可以这样排序，因为字符串长度相同，对应位置字符ASCII码大小关系和数字大小关系一致）
     * <p>
     * ！！！排序后的时间序列哪些位置存在差值
     * - 普通数字 只有相邻两两数字的时间差
     * - 时间 首尾时间差
     * <p>
     * 普通排序数字：
     * - 只有相邻两两数字的时间差
     * <p>
     * 排序时间：
     * - 相邻两两字符串的时间差
     * - 首尾两个字符串的时间差（因为时间是轮回，0点其实是24点，1点是25点）
     * - 轮回不是进制，进制要进1是螺旋上升的，轮回就是在转圈
     *
     * 鸽巢原理：如果要把n+1个物体放进n个盒子，那么至少有1个盒子包含2个或更多的物体。
     * - 共有24*60=1440种不同的分钟数，如果timePoints.size()>1440, 则一定存在相同的分钟数，相同的时间
     *
     * 轮回的过程：
     * - 考虑首尾差值
     * - 考虑使用鸽巢原理简化
     */
    public int findMinDifference(List<String> timePoints) {
        // 鸽巢原理优化
        if(timePoints.size()>24*60){
            return 0;
        }
        // 直接对字符串排序
        Collections.sort(timePoints);
        // 求最小值设置为最大值
        int minDiff = Integer.MAX_VALUE;
        int p0Minute = getMinutes(timePoints.get(0));
        int pMinute = p0Minute;
        // 相邻两两字符串的时间差
        for (int i = 1; i < timePoints.size(); i++) {
            int cMinute = getMinutes(timePoints.get(i));
            int diff = Math.abs(pMinute - cMinute);
            minDiff = Math.min(diff, minDiff);
            pMinute = cMinute;
        }
        // 首尾两个字符串的时间差 1440=24*60
        minDiff = Math.min(minDiff, Math.abs(p0Minute + 1440 - pMinute));
        return minDiff;
    }

    /**
     * ASCII码的差值等于数字的差值
     */
    public int getMinutes(String timePoint) {
        return ((timePoint.charAt(0) - '0') * 10 + (timePoint.charAt(1) - '0')) * 60 + (timePoint.charAt(3) - '0') * 10 + (timePoint.charAt(4) - '0');
    }
}
