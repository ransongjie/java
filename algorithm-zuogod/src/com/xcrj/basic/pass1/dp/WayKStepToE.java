package com.xcrj.basic.pass1.dp;

import java.util.Arrays;

/**
 * 从s必须走k步到达e的方法种数
 * N=5 1 2 3 4 5
 * s=2 
 * e=4
 * k=4
 */
public class WayKStepToE{
    public static void main(String[] args) {
        // int N=5;
        // int E=4;
        // int s=2;
        // int k=4;
        // System.out.println(waysKStepToE(N,E, k, s));
        // System.out.println(waysKStepToE2(N,E, k, s));
        // System.out.println(waysKStepToE3(N,E, k, s));

        int times=10000;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getE(maxV);
            int way1=waysKStepToE2(element.N, element.E, element.k, element.s);
            int way2=waysKStepToE3(element.N, element.E, element.k, element.s);
            if(way1!=way2){
                System.out.println("not good");
                System.out.println("N="+element.N+", E="+element.E+", k="+element.k+", s="+element.s);
                System.out.println(way1);
                System.out.println(way2);
                throw new RuntimeException();
            }
        }
    }

    /**
     * 暴力递归
     * 目标：
     * 尝试：走左边或者走右边
     */
    public static int waysKStepToE(int N,int E,int k,int s) {
        //1~N
        if(s==N+1) return 0;//
        if(s==0) return 0;//
        if(k==0){
            return s==E?1:0;
        }
        if(s==1){//只能往右走
            return waysKStepToE(N, E, k-1, s+1);
        }
        if(s==N){//只能往左走
            return waysKStepToE(N, E, k-1, s-1);
        }

        return waysKStepToE(N, E, k-1, s+1)+waysKStepToE(N, E, k-1, s-1);
    }

    /**
     * 记忆搜索动态规划
     * 缓存大小
     * 初始状态
     * 命中缓存
     * 更新缓存
     */
    public static int waysKStepToE2(int N,int E,int k,int s) {
        int[][]dp=new int[k+1][N+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        return memSearch(N, E, k, s, dp);
    }

    public static int memSearch(int N,int E,int k,int s,int[][]dp) {
        //1~N
        if(s==N+1) return 0;//
        if(s==0) return 0;//
        if(dp[k][s]!=-1)return dp[k][s];

        if(k==0){
            dp[k][s]=s==E?1:0;
            return dp[k][s];
        }
        if(s==1){//只能往右走
            dp[k][s]=memSearch(N, E, k-1, s+1,dp);
            return dp[k][s];
        }
        if(s==N){//只能往左走
            dp[k][s]=memSearch(N, E, k-1, s-1,dp);
            return dp[k][s];
        }
        dp[k][s]=memSearch(N, E, k-1, s+1,dp)+memSearch(N, E, k-1, s-1,dp);
        return dp[k][s];
    }

    /**
     * 表结构动态规划
     * 拷贝暴力递归
     * 表大小
     * 目标位置
     * 已知状态
     * - 暴力递归base case
     * - 边界条件
     * 状态转移
     * - 可变参数数量=for循环数量
     * - 顺序
     * - 注意边界问题
     */
    public static int waysKStepToE3(int N,int E,int k,int s) {
        int[][]dp=new int[k+1][N+1];
        dp[0][E]=1;

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
                dp[i][j]=dp[i-1][j+1]+dp[i-1][j-1];
            }
        }

        // return dp[k][E];
        return dp[k][s];//与memSearch目标一样
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
}
