package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 098. 路径的数目
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 注意，机器人只能往下或右走
 * 输入，方格大小m,n
 * 输出，从方格左上角走到方格右下角的路径数量
 */
public class Solution98 {
    // 动态规划
    public int uniquePaths1(int m, int n) {
        // 动态规划数组。fss[i][j]表示从左上角(0,0)走到(i,j)的路径数量
        int[][] fss=new int[m][n];

        // 边界状态。
        // 一直往下走
        for(int i=0;i<m;i++)fss[i][0]=1;
        // 一直往右走
        for(int j=0;j<n;j++)fss[0][j]=1;

        // 状态转移方程
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // 两种情况，同j从上走到i，同i从左走到j
                fss[i][j]=fss[i-1][j]+fss[i][j-1];
            }
        }

        return fss[m-1][n-1];
    }

    /**
     * 组合数学：
     * 从左上角到右下角的过程中，我们需要移动m+n−2次（目标点和出发点各占用1个格子），其中有m−1次向下移动，n−1次向右移动。
     * 因此路径的总数，就等于从m+n−2次移动中选择m−1次向下移动的方案数，即组合数。
     * 组合数的求解公式：$C_{m+n-2}^{m-1}\frac{(m+n-2)!}{(m-1)!(n-1)!}$
     * 计算公式：$\frac{(m+n-2)(m+n-3)...(n+1)(n)}{(m-1)(m-2)...(2)(1)}$，上下都有m-1项，分母从1开始到m-1，分子从n开始到m+n-2，使用r记录之前的结果
     */
    public int uniquePaths2(int m, int n) {
        // xcrj
        long r=1;
        // a是分子，b是分母，a从n开始m+n-2，b从1开始到m-1
        for(int a=n,b=1;b<m;a++,b++) r=r*a/b;

        return (int)r;
    }
}
