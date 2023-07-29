package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Solution39 {
    /**
     * 枚举宽度
     * - 先定左边界再定右边界
     * - 在左右边界内寻找最矮直方图
     */
    public int largestRectangleArea1(int[] heights) {
        // 结果
        int maxArea = Integer.MIN_VALUE;
        // 左边界从0开始
        for (int l = 0; l < heights.length; l++) {
            // 左右边界内的最小高度
            int minHeight = Integer.MAX_VALUE;
            // 右边界从左边界开始
            for (int r = l; r < heights.length; r++) {
                minHeight = Math.min(minHeight, heights[r]);
                maxArea = Math.max(maxArea, (r - l + 1) * minHeight);
            }
        }
        return maxArea;
    }

    /**
     * 枚举高度: 能够从i最多向左向右扩展多少距离
     * - 高度更高则继续拓展 则左右宽度
     */
    public int largestRectangleArea2(int[] heights) {
        // 结果
        int maxArea = Integer.MIN_VALUE;
        // 枚举
        for (int i = 0; i < heights.length; i++) {
            // 高度
            int height = heights[i];
            int l = i;
            int r = i;
            // 确定左边界
            while (l - 1 > 0 && heights[l - 1] >= height) l--;
            // 确定右边界
            while (r + 1 < heights.length && heights[r + 1] >= height) r++;
            maxArea = Math.max(maxArea, (r - l + 1) * height);
        }
        return maxArea;
    }

    /**
     * 单调栈：第i个滑块能左右滑动的最远距离
     * - 扩展左边界：从左往右遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
     * - 扩展右边界：从右往左遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明右边界不能再继续扩展
     */
    public int largestRectangleArea3(int[] heights) {
        // ls[i]=leftBound
        int[] ls = new int[heights.length];
        // rs[i]=rightBound
        int[] rs = new int[heights.length];
        // 单调递增栈
        int top = -1;
        int[] stack = new int[heights.length];
        // 扩展左边界：从左往右遍历线性表
        for (int i = 0; i < heights.length; i++) {
            // heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
            while (top != -1 && heights[i] <= heights[stack[top]]) {
                top--;
            }
            // 记录i的左边界
            ls[i] = (top == -1 ? -1 : stack[top]);
            // 入栈i
            stack[++top] = i;
        }

        // 清空栈
        top = -1;
        Arrays.fill(stack, 0);

        // 扩展右边界：从右往左遍历线性表
        for (int i = heights.length - 1; i >= 0; i--) {
            // heights[i]<=栈顶元素则出栈 直到小于情况发生，证明右边界不能再继续扩展
            while (top != -1 && heights[i] <= heights[stack[top]]) {
                top--;
            }
            // 记录i的右边界
            rs[i] = (top == -1 ? heights.length : stack[top]);
            // 入栈i
            stack[++top] = i;
        }

        // 计算结果
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, (rs[i] - ls[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    /**
     * 优化单调栈：第i个滑块能左右滑动的最远距离
     * - 确定i的左边界：第i个滑块入栈时确定了左边界，栈结构一直出栈（往左）直到有个滑块比第i个滑块矮。第i个滑块在线性表中。
     * - 确定i的右边界：第i个滑块出栈确定了右边界，线性表结构一直（往右）遍历直到有个滑块比第i个滑块矮或等高。第i个滑块在单调栈中。
     * - i右边界初始为右边界为n
     * <p>
     * - 第i个滑块在线性表中时，入栈时确定了它的左边界；
     * - 第i个滑块在单调栈中时，出栈确定了它的伪右边界（>=heights[i]的右边界不是<heights[i]的右边界）
     * - 伪右边界对最终结果不会有影响，因为，求的是maxArea，heights[j]=heights[i]的最右边j，可以找到真正的右边界
     * - i右边界初始为右边界为n
     */
    public int largestRectangleArea4(int[] heights) {
        // ls[i]=leftBound
        int[] ls = new int[heights.length];
        // rs[i]=rightBound
        int[] rs = new int[heights.length];
        // ！i右边界初始为右边界为n
        Arrays.fill(rs, heights.length);
        // 单调递增栈
        int top = -1;
        int[] stack = new int[heights.length];
        // 扩展左边界：从左往右遍历线性表
        for (int i = 0; i < heights.length; i++) {
            // heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
            while (top != -1 && heights[i] <= heights[stack[top]]) {
                rs[stack[top]] = i;
                top--;
            }
            // 记录i的左边界
            ls[i] = (top == -1 ? -1 : stack[top]);
            // 入栈i
            stack[++top] = i;
        }

        // 计算结果
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, (rs[i] - ls[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        System.out.println(solution39.largestRectangleArea2(new int[]{1, 1}));
    }
}
