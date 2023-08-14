package com.xcrj.company.pass1;

/**
 * s中t子串的数量
 *
 *
 */
public class A025WaySubStr1Str2 {

    public static int waySubStr1Str2(String s,String t){
        int m=s.length(),n=t.length();
        //dp[i][j]=s[0~i]中t[0~j]的数量
        int[][]dp=new int[m+1][n+1];
        for (int i = 0; i < m+1; i++) {
            dp[i][0]=1;
        }
        for (int i = 0; i < n+1; i++) {
            dp[0][i]=0;
        }
        dp[0][0]=1;

        for (int i = 1; i < m+1; i++) {//i=1
            for (int j = 1; j < n+1; j++) {//j=1
                //s.charAt(i-1)==t.charAt(j-1) i j是字符数量
                dp[i][j]=dp[i-1][j]+(s.charAt(i-1)==t.charAt(j-1)?dp[i-1][j-1]:0);
            }
        }

        return dp[m][n];
    }

    public static int waySubStr1Str22(String s,String t){
        int m=s.length();
        int n=t.length();
        int[]dp=new int[n+1];
        dp[0]=1;
        //第0行
        for (int i = 1; i < n + 1; i++) {
            dp[i]=0;
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = n; j >=1 ; j--) {
                //倒序，存在dp[j-1]（dp[i-1][j-1]）
                //dp[i][j]=dp[i-1][j]+(s.charAt(i-1)==t.charAt(j-1)?dp[i-1][j-1]:0);
                dp[j]=dp[j]+(s.charAt(i-1)==t.charAt(j-1)?dp[j-1]:0);
            }
        }

        return dp[n];
    }

}
