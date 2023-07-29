package com.xcrj.company.pass1;

/**
 * 最多划的线数量 转化 求A和B的最长公共子序列
 */
public class A077MaxUncrossedLines {
    /**
     * @param as
     * @param bs
     * @return
     */
    public static int maxUncrossedLines(int[]as,int[] bs){
        if(as==null||as.length==0||bs==null||bs.length==0){
            return 0;
        }
        int m=as.length,n=bs.length;
        //as[0,i] bs[0,j]的最长公共子序列
        int[][]dp=new int[m][n];
        //as[0,0] bs[0,0]
        dp[0][0]=as[0]==bs[0]?1:0;
        //as[0,0] bs[0,j]
        for (int j = 1; j < n; j++) {//从1开始
            //不等就 as[0,0] bs[0,j-1]中找最长公共子序列
            dp[0][j]=as[0]==bs[j]?1:dp[0][j-1];
        }
        //as[0,i] bs[0,0]
        for (int i = 1; i < m; i++) {//从1开始
            dp[i][0]=as[i]==bs[0]?1:dp[i-1][0];
        }
        //
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //不考虑as[i]求 as[0~i-1]和bs[0~j]的最长公共子序列
                int p1=dp[i-1][j];
                //不考虑bs[j]求 as[0~i]和bs[0~j-1]的最长公共子序列
                int p2=dp[i][j-1];
                //考虑as[j]必须等于bs[j]
                int p3=as[i]==bs[j]?(1+dp[i-1][j-1]):0;
                dp[i][j]=Math.max(Math.max(p1,p2),p3);
            }
        }

        //as[0,m-1] bs[0,n-1]
        return dp[m-1][n-1];
    }
}
