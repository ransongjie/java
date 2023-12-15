package com.xcrj.basic.pass2.dp;

import java.util.Arrays;

/**
 * 从s必须走k步到达e的方法种数
 * N=5 1 2 3 4 5
 * s=2 
 * e=4
 * k=4
 */
public class WayKStepToE {

    /***
     * 暴力递归
     * try: 走左边或者走右边
     * base case
     * 特殊情况
     * 一般情况
     * @param N 1~N
     * @param s 
     * @param e 
     * @param k 
     */
    public static int wayKStepToE(int N,int s,int e,int k) {
        if(s==N+1) return 0;
        if(s==0) return 0;
        if(k==0){
            return s==e?1:0;
        }

        if(s==1){
            return wayKStepToE(N, s+1, e, k-1);
        }
        if(s==N){
            return wayKStepToE(N, s-1, e, k-1);
        }

        return wayKStepToE(N, s+1, e, k-1)+wayKStepToE(N, s-1, e, k-1);
    }

    /**
     * 记忆搜索
     * 加缓存
     * 命中缓存
     * 没有命中缓存
     */
    public static int wayKStepToE2(int N,int s,int e,int k) {
        int[][]dp=new int[k+1][N+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        return proccess(N,dp,s,e,k);
    }

    public static int proccess(int N,int[][]dp,int s,int e,int k) {
        if(s==N+1) return 0;
        if(s==0) return 0;
        if(dp[k][s]!=-1) return dp[k][s];
        if(k==0){
            dp[k][s]=s==e?1:0;
            return dp[k][s];
        }
        if(s==1){
            dp[k][s]=proccess(N, dp, s+1, e, k-1);
            return dp[k][s];
        }
        if(s==N){
            dp[k][s]=proccess(N, dp, s-1, e, k-1);
            return dp[k][s];
        }

        dp[k][s]=proccess(N, dp, s+1, e, k-1)+proccess(N, dp, s-1, e, k-1);
        return dp[k][s];
    }

    /**
     * 动态规划
     * dp数组：数组大小，目标位置，已知位置，递推关系，递推顺序
     */
    public static int wayKStepToE3(int N,int s,int e,int k) {
        int[][]dp=new int[k+1][N+1];
        dp[0][e]=1;//k=0,除开e，其它位置都是0

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= N; j++) {
                if(j==1){
                    if(j+1==N+1) continue;
                    dp[i][j]=dp[i-1][j+1];
                    continue;
                }
                if(j==N){
                    if(j-1==0) continue;
                    dp[i][j]=dp[i-1][j-1];
                    continue;
                }
                dp[i][j]=dp[i-1][j-1]+dp[i-1][j+1];
            }
        }

        return dp[k][s];
    }

    static class Element{
        int N;
        int E;
        int s;
        int k;
    }
    public static Element getE(int maxV) {
        int N=(int)(1+Math.random()*maxV);
        int k=(int)(Math.random()*maxV);
        int E=(int)(1+Math.random()*N);
        int s=(int)(1+Math.random()*N);
        Element element=new Element();
        element.N=N;
        element.E=E;
        element.s=s;
        element.k=k;
        return element;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getE(maxV);
            int way1=wayKStepToE(element.N, element.s,element.E, element.k);
            int way2=wayKStepToE2(element.N, element.s,element.E, element.k);
            int way3=wayKStepToE3(element.N, element.s,element.E, element.k);
            if(way1!=way2||way2!=way3||way1!=way3){
                System.out.println("not good");
                System.out.println("N="+element.N+", E="+element.E+", k="+element.k+", s="+element.s);
                System.out.println(way1);
                System.out.println(way2);
                System.out.println(way3);
                throw new RuntimeException();
            }
        }
    }
}
