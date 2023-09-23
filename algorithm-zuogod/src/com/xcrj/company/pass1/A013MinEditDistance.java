package com.xcrj.company.pass1;

/**
 * 最短编辑距离(两字符串的相似度)
 */
public class A013MinEditDistance {

    /**
     *
     * @param s1
     * @param s2
     * @param add 增加代价
     * @param del
     * @param rep 替换代价
     * @return
     */
    public static int editDistance(String s1,String s2,int add,int del,int rep){
        int m=s1.length()+1;
        int n=s2.length()+1;
        int[][]dp=new int[m][n];
        dp[0][0]=0;
        for (int i = 1; i < m; i++) {
            dp[0][i]=i*add;
        }
        for (int i = 1; i < n; i++) {
            dp[i][0]=i*del;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=dp[i-1][j-1]+(s1.charAt(i-1)==s2.charAt(j-1)?0:rep);
                dp[i][j]=Math.min(dp[i][j],dp[i-1][j]+del);//s1 del i
                dp[i][j]=Math.min(dp[i][j],dp[i][j-1]+add);//s1 add j
            }
        }

        return dp[m-1][n-1];
    }
}
