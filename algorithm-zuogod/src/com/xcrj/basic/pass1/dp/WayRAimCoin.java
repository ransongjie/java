package com.xcrj.basic.pass1.dp;

import java.util.Arrays;

/**
 * 组成目标值的硬币数量种数
 * 可重复选取同一个硬币(0~n次)
 * coins={硬币币值}
 * aim 目标值
 */
public class WayRAimCoin {
    public static void main(String[] args) {
        // int[] coins={2,4,7,3,5};
        // int aim=10;
        // System.out.println(wayRAimCoin(coins, aim, 0));
        // System.out.println(wayRAimCoin3(coins, aim, 0));
        // System.out.println(wayRAimCoin4(coins, aim, 0));

        int times=10000;
        int maxLen=20;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getCoins(maxLen, maxV);
            int way1=wayRAimCoin3(element.coins, element.aim, 0);
            int way2=wayRAimCoin4(element.coins, element.aim, 0);
            if(way1!=way2){
                System.out.println("not good");
                System.out.println(Arrays.toString(element.coins)+", aim="+element.aim);
                System.out.println(way1);
                System.out.println(way2);
                throw new RuntimeException();
            }
        }
    }

    /**
     * 暴力递归
     * 目标
     * 尝试
     */
    public static int wayRAimCoin(int[]coins,int aim,int idx) {
        if(aim<0) return 0;
        // if(aim==0) return 1;
        // if(idx==coins.length) return 0;
        if(idx==coins.length) return aim==0?1:0;

        int r=0;
        for (int i = 0; i <= (aim/coins[idx]); i++) {
            r+=wayRAimCoin(coins, aim-i*coins[idx], idx+1);
        }

        return r;
    }

    /**
     * 动态规划
     * 拷贝暴力递归
     * dp大小
     * 目标位置
     * 初始状态
     * 状态转移
     */
    public static int wayRAimCoin3(int[]coins,int aim,int idx) {
        int N=coins.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;

        for (int i = N-1; i >-1 ; i--) {
            for (int j = 0; j < aim+1; j++) {
                //存在枚举行为
                for (int k = 0; k <= (j/coins[i]); k++) {
                    dp[i][j]+=dp[i+1][j-k*coins[i]];
                }
            }
        }

        return dp[0][aim];
    }

    /**
     * 动态规划
     */
    public static int wayRAimCoin4(int[]coins,int aim,int idx) {
        int N=coins.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;

        for (int i = N-1; i >-1 ; i--) {
            for (int j = 0; j < aim+1; j++) {
                //优化枚举行为
                if(j-coins[i]>=0){
                    dp[i][j]=dp[i][j-coins[i]]+dp[i+1][j];
                }else{
                    dp[i][j]=dp[i+1][j];//0+dp[i+1][j]
                }
            }
        }

        return dp[0][aim];
    }

    static class Element{
        int[] coins;
        int aim;
    }
    public static Element getCoins(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] coins=new int[len];
        for (int i = 0; i < coins.length; i++) {
            int v=(int)(1+Math.random()*(maxV-1));
            coins[i]=v;
        }

        int aim=(int)(Math.random()*maxV*2);
        Element element=new Element();
        element.coins=coins;
        element.aim=aim;
        return element;
    }
}
