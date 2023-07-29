package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 099. 最小路径之和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 一个机器人每次只能向下或者向右移动一步。
 * 注意，机器人只能往下或右走
 * 输入，grid[i][j]=权重
 * 输出，从左上角 到 右下角 的最小路径权重和
 */
public class Solution99 {
    // 动态规划
    public int minPathSum(int[][] grid) {
        if(null==grid)return 0;
        if(0==grid.length)return 0;
        if(0==grid[0].length)return 0;

        // 动态规划数组。fss[i][j]表示从左上角到(i,j)的最小权重和
        int row=grid.length;
        int column=grid[0].length;
        int[][] fss=new int[row][column];

        // 边界状态
        // 出发点
        fss[0][0]=grid[0][0];
        // 一直往下走
        for(int i=1;i<row;i++)fss[i][0]=fss[i-1][0]+grid[i][0];
        // 一直往右走
        for(int j=1;j<column;j++)fss[0][j]=fss[0][j-1]+grid[0][j];

        // 状态转移
        for(int i=1;i<row;i++){
            for(int j=1;j<column;j++){
                // 同j从上到i，同i从左到j
                fss[i][j]=Math.min(fss[i-1][j],fss[i][j-1])+grid[i][j];
            }
        }

        return fss[row-1][column-1];
    }
}
