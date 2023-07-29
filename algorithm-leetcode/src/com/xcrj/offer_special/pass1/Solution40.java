package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 040. 矩阵中最大的矩形
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找中只包含 1 的最大矩形，并返回其面积。
 * 注意：此题 matrix 输入格式为一维 01 字符串数组。
 * <p>
 * Solution13：使用二维前缀和
 * 给定一个二维矩阵 matrix，计算其子矩形范围内元素的总和
 * 已知：该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * <p>
 * 本质：矩阵中最大的矩形。以每列为底的直方图最大矩形面积。直方图最大矩形面积是solution39
 */
public class Solution40 {

    /**
     * 左横向直方图最大矩形面积：
     * - 统计第i行的高度，连续1的个数（得到直方图的所有高度）
     * - 以(i,j)为右端点向上寻找能滑动的最大距离（得到直方图的宽度）
     */
    public int maximalRectangle1(String[] matrix) {
        // 特殊情况处理
        if (matrix.length == 0) return 0;
        // 统计第i行的高度，连续1的个数（得到直方图的所有高度）
        int[][] heights = new int[matrix.length][matrix[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length(); j++) {
                // 连续1的个数
                if (matrix[i].charAt(j) == '1') {
                    // j==0, 初始条件处理
                    heights[i][j] = (j == 0 ? 1 : (heights[i][j - 1] + 1));
                }
            }
        }

        // ！求最大赋值为最小情况0
        int maxRectangle = 0;
        // 以(i,j)为右端点向上寻找能滑动的最大距离（得到直方图的宽度）
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length(); j++) {
                // 处理不能拓展的点
                if (matrix[i].charAt(j) == '0') continue;
                // 1个高
                int h = heights[i][j];
                // 更多高，求最小高
                for (int k = i; k >= 0; k--) {
                    h = Math.min(h, heights[k][j]);
                    // i-k+1为宽度
                    maxRectangle = Math.max(maxRectangle, (i - k + 1) * h);
                }
            }
        }

        return maxRectangle;
    }

    /**
     * 左横向直方图最大矩形面积
     * - 统计第i行的高度，连续1的个数（得到直方图的所有高度）
     * - 每1列为底应用单调栈方法：第i个滑块能左右滑动的最远距离
     * -- 扩展左边界：从左往右遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
     * -- 扩展右边界：从右往左遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明右边界不能再继续扩展
     */
    public int maximalRectangle2(String[] matrix) {
        // 特殊情况处理
        if (matrix.length == 0) return 0;
        // 统计所有高度
        int[][] heights = new int[matrix.length][matrix[0].length()];
        // 统计第i行的高度，连续1的个数（得到直方图的所有高度）
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length(); j++) {
                if (matrix[i].charAt(j) == '1') {
                    // 初始情况处理j==0
                    heights[i][j] = (j == 0 ? 1 : heights[i][j - 1] + 1);
                }
            }
        }

        // ！求最大赋值为最小情况0
        int maxRectangle = 0;
        // 第j列为底应用单调栈方法：第i个滑块能左右滑动的最远距离
        for (int j = 0; j < matrix[0].length(); j++) {
            // 单调递减栈
            int top = -1;
            int[] stack = new int[matrix.length];
            // 记录i左边界
            int[] ls = new int[matrix.length];
            // 记录i右边界
            int[] rs = new int[matrix.length];
            /// 扩展左边界：从左往右遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
            for (int i = 0; i < matrix.length; i++) {
                while (-1 != top && heights[i][j] <= heights[stack[top]][j]) {
                    top--;
                }
                // 初始情况处理，空栈，-1 == top
                ls[i] = (-1 == top ? -1 : stack[top]);
                stack[++top] = i;
            }

            // 清理栈
            top = -1;
            stack = new int[matrix.length];

            /// 扩展右边界：从右往左遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明右边界不能再继续扩展
            for (int i = matrix.length - 1; i >= 0; i--) {
                while (-1 != top && heights[i][j] <= heights[stack[top]][j]) {
                    top--;
                }
                // 初始情况处理，空栈，-1 == top
                rs[i] = (-1 == top ? matrix.length : stack[top]);
                stack[++top] = i;
            }

            int maxAreaj = Integer.MIN_VALUE;
            // 第j列为底的直方图最大面积
            for (int i = 0; i < matrix.length; i++) {
                int hj = heights[i][j];
                maxAreaj = Math.max(maxAreaj, (rs[i] - ls[i] - 1) * hj);
            }

            maxRectangle = Math.max(maxRectangle, maxAreaj);
        }

        return maxRectangle;
    }

    /**
     * 左横向直方图最大矩形面积
     * - 统计第i行的高度，连续1的个数（得到直方图的所有高度）
     * - 每1列为底应用优化单调栈：第i个滑块能左右滑动的最远距离
     * -- 确定i的左边界：第i个滑块入栈时确定了左边界，栈结构一直出栈（往左）直到有个滑块比第i个滑块矮。第i个滑块在线性表中。
     * -- 确定i的右边界：第i个滑块出栈确定了右边界，线性表结构一直（往右）遍历直到有个滑块比第i个滑块矮或等高。第i个滑块在单调栈中。
     * -- i右边界初始为右边界为n
     */
    public int maximalRectangle3(String[] matrix) {
        // 特殊情况处理
        if (matrix.length == 0) return 0;
        // 统计所有高度
        int[][] heights = new int[matrix.length][matrix[0].length()];
        // 统计第i行的高度，连续1的个数（得到直方图的所有高度）
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length(); j++) {
                if (matrix[i].charAt(j) == '1') {
                    // 初始情况处理j==0
                    heights[i][j] = (j == 0 ? 1 : heights[i][j - 1] + 1);
                }
            }
        }

        // ！求最大赋值为最小情况0
        int maxRectangle = 0;
        // 第j列为底应用单调栈方法：第i个滑块能左右滑动的最远距离
        for (int j = 0; j < matrix[0].length(); j++) {
            // 单调递减栈
            int top = -1;
            int[] stack = new int[matrix.length];
            // 记录i左边界
            int[] ls = new int[matrix.length];
            // 记录i右边界
            int[] rs = new int[matrix.length];
            // i右边界初始为右边界为n
            Arrays.fill(rs, matrix.length);

            /// 确定i的左边界：第i个滑块入栈时确定了左边界，栈结构一直出栈（往左）直到有个滑块比第i个滑块矮。第i个滑块在线性表中。
            for (int i = 0; i < matrix.length; i++) {
                while (-1 != top && heights[i][j] <= heights[stack[top]][j]) {
                    /// 确定i的右边界：第i个滑块出栈确定了右边界，线性表结构一直（往右）遍历直到有个滑块比第i个滑块矮或等高。第i个滑块在单调栈中。
                    rs[stack[top]] = i;
                    top--;
                }
                // 初始情况处理，空栈，-1 == top
                ls[i] = (-1 == top ? -1 : stack[top]);
                stack[++top] = i;
            }

            int maxAreaj = Integer.MIN_VALUE;
            // 第j列为底的直方图最大面积
            for (int i = 0; i < matrix.length; i++) {
                int hj = heights[i][j];
                maxAreaj = Math.max(maxAreaj, (rs[i] - ls[i] - 1) * hj);
            }

            maxRectangle = Math.max(maxRectangle, maxAreaj);
        }

        return maxRectangle;
    }

    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.maximalRectangle1(new String[]{"1"}));
    }
}
