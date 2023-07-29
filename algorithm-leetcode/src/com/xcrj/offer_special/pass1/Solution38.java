package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 038. 每日温度
 * 请根据每日 气温 列表 temperatures，重新生成一个列表，要求其对应位置的输出为：
 * 要想观测到更高的气温-温度更高
 * 至少需要等待的天数-最小日期，更大日期
 * 如果气温在这之后都不会升高，请在该位置用0 来代替。
 */
public class Solution38 {
    /**
     * 区别：使用 线性表 times[温度]=日期 记录温度对应的日期
     * <p>
     * 倒序寻找温度更高的最小日期
     * - 温度更高：temperatures[i] + 1~100
     * - 最小日期：times[温度]=日期 记录温度对应的日期
     * - 更大日期：倒序保证更大日期
     * <p>
     * 将日期和温度分开记录
     * - 记录温度：temperatures[日期]=温度
     * - 记录日期：times[温度]=日期
     * <p>
     * 已知：
     * - 温度 in [30,100]
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        // 结果
        int[] rs = new int[temperatures.length];
        // 记录日期, 101是0度~100度
        int[] times = new int[101];
        // 最小日期
        Arrays.fill(times, Integer.MAX_VALUE);
        // 倒序
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int minTime = Integer.MAX_VALUE;
            // 寻找温度更高
            for (int j = temperatures[i] + 1; j <= 100; j++) {
                // 的最小日期
                if (times[j] < minTime) {
                    minTime = times[j];
                }
            }
            // 记录结果
            if (minTime != Integer.MAX_VALUE) rs[i] = minTime - i;

            // times[温度]=日期
            times[temperatures[i]] = i;
        }

        return rs;
    }

    /**
     * 区别：使用 单调栈 记录stack{递减温度下标(日期)}
     * <p>
     * 栈存储更低温度的下标(日期)
     * - 温度更高：线性表temperatures[i] > temperatures[栈stack[top]]
     * - 最小日期：正序依次遍历线性表，依次比较单调栈
     * - 更大日期：正序依次遍历线性表
     * <p>
     * 单调栈：记录单调递增或单调递减的栈
     * 单调栈使用：遍历线性表，栈存储已经遍历过的元素，将栈中的元素与正在遍历的线性表中的第i个元素对比，更大的出栈
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        // 结果
        int[] rs = new int[temperatures.length];
        // 单调栈
        int top = -1;
        int[] stack = new int[temperatures.length];
        // 遍历线性表
        for (int i = 0; i < temperatures.length; i++) {
            //  寻找温度更高
            while (top != -1 && temperatures[i] > temperatures[stack[top]]) {
                // 的最小日期
                int j = stack[top--];
                rs[j] = i - j;
            }
            // 入栈温度更低日期
            stack[++top] = i;
        }
        return rs;
    }
}
