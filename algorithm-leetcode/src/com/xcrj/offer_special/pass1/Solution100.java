package com.xcrj.offer_special.pass1;

import java.util.List;

/**
 * 剑指 Offer II 100. 三角形中最小路径之和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 分析：
 * - 要求，找出自顶向下的最小路径和。到底部即可，三角形有底边，因此需要判断每种到达底边的路径和方式的最小值
 * - 第1行有1个元素，第2行2个元素，...，第n行n个元素
 * <p>
 * 三角形形状
 * [2]
 * [3,4]
 * [6,5,7]
 * [4,1,8,3]
 */
public class Solution100 {
    /**
     * 动态规划数组：
     * - f[i][j]表示从三角形顶部走到位置(i,j)的最小路径和
     * <p>
     * 边界条件
     * - f[0][0]=c[0][0]
     * <p>
     * 状态转移方程
     * - f[i][0]=f[i−1][0]+c[i][0]，当j=0时f[i−1][j−1]无用，f[i][0]已经是最左侧，上一行不能再继续向左侧靠拢
     * - f[i][i]=f[i−1][i−1]+c[i][i]，当j=i时f[i−1][j]无用，f[i][i]已经在最右侧，上一行不能没有元素
     * - f[i][j]=min(f[i−1][j−1],f[i−1][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
     * <p>
     * 空间压缩
     * -
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        // 定义动态规划数组
        // f[i][j]表示从三角形顶部走到位置(i,j)的最小路径和
        int[][] f = new int[triangle.size()][triangle.size()];

        // 边界条件f[0][0]=c[0][0]
        f[0][0] = triangle.get(0).get(0);

        // 从1开始，因为0已经处理过
        for (int i = 1; i < triangle.size(); i++) {
            // f[i][0]=f[i−1][0]+c[i][0]，当j=0时f[i−1][j−1]无用，f[i][0]已经是最左侧，上一行不能再继续向左侧靠拢
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            // <i, 因为三角形
            for (int j = 1; j < i; j++) {
                // f[i][j]=min(f[i−1][j−1],f[i−1][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
                f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle.get(i).get(j);
            }
            // f[i][i]=f[i−1][i−1]+c[i][i]，当j=i时f[i−1][j]无用，f[i][i]已经在最右侧，上一行没有元素
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }

        // 求不同到达底边方式的最小路径和的最小值
        int minTotal = f[triangle.size() - 1][0];
        // 从0开始，j=0已经处理过
        for (int j = 1; j < triangle.size(); j++) {
            minTotal = Math.min(minTotal, f[triangle.size() - 1][j]);
        }

        return minTotal;
    }

    /**
     * 空间压缩
     * - 根据状态转移方程可知，f[i][]只和f[i-1][]有关
     * - 使用f[2][n]，存储前一行f[i-1][]和当前行f[i][]即可
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 定义动态规划数组
        // f[i][j]表示从三角形顶部走到位置(i,j)的最小路径和
        // new int[2][n]，f[0][]和f[1][]交替代表当前行和前一行
        int[][] f = new int[2][triangle.size()];

        // 边界条件f[0][0]=c[0][0]
        f[0][0] = triangle.get(0).get(0);

        // 从1开始，因为0已经处理过
        for (int i = 1; i < triangle.size(); i++) {
            // ！使用%2运算控制f[0][],f[1][]谁是前一行，谁是后一行
            // 初始curr=1,第1行。prev=0，第0行
            int curr = i % 2;
            int prev = 1 - curr;
            // f[i][0]=f[i−1][0]+c[i][0]，当j=0时f[i−1][j−1]无用，f[i][0]已经是最左侧，上一行不能再继续向左侧靠拢
            // f[curr][0]=f[prev][0]+c[i][0]，当j=0时f[prev][j−1]无用，f[curr][0]已经是最左侧，上一行不能再继续向左侧靠拢
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            // <i, 因为三角形
            for (int j = 1; j < i; j++) {
                // f[i][j]=min(f[i−1][j−1],f[i−1][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
                // f[curr][j]=min(f[prev][j−1],f[prev][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            // f[i][i]=f[i−1][i−1]+c[i][i]，当j=i时f[i-1][j]无用，f[i][i]已经在最右侧，上一行第i列没有元素
            // f[curr][i]=f[prev][i−1]+c[i][i]，当j=i时f[prev][j]无用，f[curr][i]已经在最右侧，上一行第i列没有元素
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }

        // 求不同到达底边方式的最小路径和的最小值
        // (n - 1) % 2获取最后一行的行号
        int minTotal = f[(triangle.size() - 1) % 2][0];
        for (int j = 1; j < triangle.size(); j++) {
            minTotal = Math.min(minTotal, f[(triangle.size() - 1) % 2][j]);
        }

        return minTotal;
    }

    /**
     * 倒序遍历列，将空间压缩到只记录从顶部走到当前行的第j列的f[n]
     * 倒序原因：
     * - 若倒序，当前行第j列值=min{上一行第j-1列值，上一行第j列值}+c[i][j]，正确
     * - 若正序，当前行第j列值=min{当前行第j-1列值,上一行第j列值}+c[i][j]，错误
     *
     * 正序过程：
     *  - 当前行第j-1列的值=min{上一行第j-2列值，上一行第j-2列值}+c[i-1][j-1]
     *  - 当前行第j列值=min{上一行第j-1列值，上一行第j列值}+c[i][j]。无法获取上一行第j-1列值，f[j-1]的值已经被更新为当前行第j-1列的值
     *
     * 正序错误原因：
     * - 因为在计算当前行第j列时，第0列到第j-1列已经被更新了，即第0列到第j-1列的值已经从上一行的值变成了当前行的值
     * - 本质上要求等号右边的是上一行的第j-1列的值，等号左边是当前行第j列的值，
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        // 定义动态规划数组
        // f[i][j]表示从三角形顶部走到位置(i,j)的最小路径和
        // f[j]表示从三角形顶部走到位置当前行第j列的最小路径和
        // new int[n],f[j]=f[j-1]+f[j], 等号右边是上一行的第x列的值，等号左边是当前行的第x列的值
        int[] f = new int[triangle.size()];

        // 边界条件f[0][0]=c[0][0]
        // 边界条件当前行第0列值=c[0][0]
        f[0] = triangle.get(0).get(0);

        // f[i][j]表示从三角形顶部走到位置(i,j)的最小路径和
        for (int i = 1; i < triangle.size(); i++) {
            // 这里也倒序了
            // f[i][i]=f[i−1][i−1]+c[i][i]，当j=i时f[i-1][j]无用，f[i][i]已经在最右侧，上一行第i列没有元素
            // f[curr][i]=f[prev][i−1]+c[i][i]，当j=i时f[prev][j]无用，f[curr][i]已经在最右侧，上一行第i列没有元素
            // f[i]=f[i-1]+c[i][i]，当前行第i列值=上一行第i-1列值+c[i][i]，上一行第i列没有元素
            f[i] = f[i - 1] + triangle.get(i).get(i);
            // 倒序，从i-1到1，上面的方法是从1到i-1
            for (int j = i - 1; j > 0; j--) {
                // f[i][j]=min(f[i−1][j−1],f[i−1][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
                // f[curr][j]=min(f[prev][j−1],f[prev][j])+c[i][j]，第i行第j列的元素值只与左上方的元素和正上方的元素有关
                // 当前行第j列值=min{上一行第j-1列值，上一行第j列值}+c[i][j]
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            // f[i][0]=f[i−1][0]+c[i][0]，当j=0时f[i−1][j−1]无用，f[i][0]已经是最左侧，上一行不能再继续向左侧靠拢
            // f[curr][0]=f[prev][0]+c[i][0]，当j=0时f[prev][j−1]无用，f[curr][0]已经是最左侧，上一行不能再继续向左侧靠拢
            // 当前行第0列值=上一行第0列值+c[i][0]
            f[0] = f[0] + triangle.get(i).get(0);
        }

        // 处理结果
        int minTotal = f[0];
        for (int j = 1; j < triangle.size(); j++) {
            minTotal = Math.min(minTotal, f[j]);
        }

        return minTotal;
    }
}
