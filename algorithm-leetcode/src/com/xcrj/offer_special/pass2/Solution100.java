package com.xcrj.offer_special.pass2;

import java.util.List;

/**
 * 剑指 Offer II 100. 三角形中最小路径之和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 分析：
 * - 第1行有1个元素，第2行2个元素，...，第n行n个元素
 * - 移动，往下 或 往右下
 * - 要求，找出自顶向下的最小路径和。到底部即可。
 * - 三角形有底边，因此需要判断每种到达底边的路径和方式的最小值
 * <p>
 * 三角形形状
 * [2]
 * [3,4]
 * [6,5,7]
 * [4,1,8,3]
 */
public class Solution100 {
    // 动态规划，边界状态，状态转移
    public int minimumTotal1(List<List<Integer>> triangle) {
        // 动态规划数组
        int[][] fss=new int[triangle.size()][triangle.size()];

        // 边界状态
        fss[0][0]=triangle.get(0).get(0);

        // 状态转移
        // i行
        for(int i=1;i<triangle.size();i++){
            // 第0列
            fss[i][0]=fss[i-1][0]+triangle.get(i).get(0);
            // j列，<i，三角形
            for(int j=1;j<i;j++){
                //  从上而来，从左上角而来
                fss[i][j]=Math.min(fss[i-1][j],fss[i-1][j-1])+triangle.get(i).get(j);
            }
            // 三角形斜边
            fss[i][i]=fss[i-1][i-1]+triangle.get(i).get(i);
        }

        // 求到达底边的最小路径和
        // xcrj 初始值
        int minSum=fss[triangle.size()-1][0];
        for(int j=1;j<triangle.size();j++)minSum=Math.min(minSum,fss[triangle.size()-1][j]);

        return minSum;
    }

    /**
     * 动态规划+滚动数组
     * - 根据状态转移方程可知，f[i][]只和f[i-1][]有关
     * - 使用f[2][n]，存储前一行f[i-1][]和当前行f[i][]即可
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 动态规划数组
        int[][] fss=new int[2][triangle.size()];

        // 边界状态
        fss[0][0]=triangle.get(0).get(0);

        // 状态转移
        // i行
        for(int i=1;i<triangle.size();i++){
            int curr=i%2;
            int prev=1-curr;
            // 第0列
            fss[curr][0]=fss[prev][0]+triangle.get(i).get(0);
            // j列，<i，三角形
            for(int j=1;j<i;j++){
                //  从上而来，从左上角而来
                fss[curr][j]=Math.min(fss[prev][j],fss[prev][j-1])+triangle.get(i).get(j);
            }
            // 三角形斜边
            fss[curr][i]=fss[prev][i-1]+triangle.get(i).get(i);
        }

        // 求到达底边的最小路径和
        // xcrj 初始值
        int minSum=fss[(triangle.size()-1)%2][0];
        for(int j=1;j<triangle.size();j++)minSum=Math.min(minSum,fss[(triangle.size()-1)%2][j]);

        return minSum;
    }

    // 动态规划+滚动数组+倒序更新
    // xcrj 什么时候使用倒序更新？下一行的值由上一行的前面的值决定，需要保证前面的值后被更新，因此倒序
    public int minimumTotal3(List<List<Integer>> triangle) {
        int[] fs = new int[triangle.size()];
        
        fs[0] = triangle.get(0).get(0);

        // 整体倒序，从最优侧斜边到最左边直边
        for (int i = 1; i < triangle.size(); i++) {
            // 最右侧，斜边
            fs[i]=fs[i-1]+triangle.get(i).get(i);
            // 倒序 先使用上一行的值，从斜边开始
            for (int j = i - 1; j > 0; j--) {
                // 右侧fs[j - 1]=fss[prev][j-1], fs[j]=fss[prev][j]
                fs[j] = Math.min(fs[j - 1], fs[j]) + triangle.get(i).get(j);
            }
            // 左下角
            fs[0]=fs[0]+triangle.get(i).get(0);
        }

        int minSum = fs[0];
        for (int j = 1; j < triangle.size(); j++) {
            minSum = Math.min(minSum, fs[j]);
        }

        return minSum;
    }
}
