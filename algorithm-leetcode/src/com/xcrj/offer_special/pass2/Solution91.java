package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 091. 粉刷房子
 * 假如有一排房子，共 n 个，
 * 每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
 * 相邻的两个房子颜色不能相同。
 * costs[0][0] 表示第 0 号房子粉刷成红色的成本花费，costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 * 
 * 请计算出粉刷完所有房子最少的花费成本。
 */
public class Solution91 {
    // 动态规划+滚动数组
    // xcrj 当前值只和前一个值相关，则可以使用滚动数组
    public int minCost(int[][] costs) {
        // xcrj 不同颜色的总成本都被记录了
        // 记录从0~当前房子，总成本
        int sum3[]=new int[3];
        
        // 初始化，第0套房子，被粉刷为3中不同颜色的成本
        for(int i=0;i<3;i++){
            sum3[i]=costs[0][i];
        }

        // 从第1套房子开始~最后一套房子，总成本
        for(int i=1;i<costs.length;i++){
            // 3种颜色
            int sum3New[]=new int[3];
            for(int j=0;j<3;j++){
                // 到当前房子被刷成红色的总成本=min(前一套房子被刷成黄色的总成本,前一套房子被刷成蓝色的总成本)+costs[i][j]
                sum3New[j]=Math.min(sum3[(j+1)%3],sum3[(j+2)%3])+costs[i][j];
            }
            sum3=sum3New;
        }

        return Arrays.stream(sum3).min().getAsInt();
    }
}
