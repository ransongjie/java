package com.xcrj.basic.pass2.dp;

import java.util.Arrays;

/**
 * 组成目标值的最少硬币数量
 * coins={硬币币值}
 * aim 目标值
 */
public class MinAimCoin {
    /**
     * 暴力递归
     * 尝试方法
     * base case
     * 特殊情况
     * 一般情况
     * @param coins
     * @param rest 剩余金额
     * @param idx
     * @return
     */
    public static int minAimCoin(int[]coins,int rest,int idx) {
        if(rest<0) return -1;
        if(rest==0) return 0;//返回硬币数量为0
        if(idx==coins.length) return -1;

        // 选idx硬币
        int pNext=minAimCoin(coins, rest-coins[idx], idx+1);
        // 不选idx硬币
        int qNext=minAimCoin(coins, rest, idx+1);

        if(pNext==-1&&qNext==-1) return -1;
        if(pNext!=-1&&qNext==-1) return pNext+1;// +1，因为选了idx硬币
        if(pNext==-1&&qNext!=-1) return qNext;

        return Math.min(pNext+1, qNext);
    }

    /**
     * 
     * @param coins
     * @param rest
     * @param idx
     * @return
     */
    public static int minAimCoin2(int[]coins,int aim,int idx) {
        int[][]dp=new int[coins.length+1][aim+1];
        for (int[] is : dp) {
            Arrays.fill(is, -2);
        }

        return proccess(coins, aim, idx,dp);
    }

    public static int proccess(int[]coins,int rest,int idx,int[][]dp) {
        if(rest<0) return -1;
        
        if(dp[idx][rest]!=-2) return dp[idx][rest];

        if(rest==0) {
            dp[idx][rest]=0;
            return dp[idx][rest];
        }
        if(idx==coins.length) {
            dp[idx][rest]=-1;
            return dp[idx][rest];
        }
        
        int pNext=proccess(coins, rest-coins[idx], idx+1,dp);
        int qNext=proccess(coins, rest, idx+1,dp);
        if(pNext==-1&&qNext==-1) {
            dp[idx][rest]=-1;
            return dp[idx][rest];
        }
        if(pNext!=-1&&qNext==-1) {
            dp[idx][rest]=pNext+1;
            return dp[idx][rest];
        }
        if(pNext==-1&&qNext!=-1) {
            dp[idx][rest]=qNext;
            return dp[idx][rest];
        }

        dp[idx][rest]=Math.min(pNext+1, qNext);
        return dp[idx][rest];
    }

    /**
     * 动态规划数组
     * 数组大小，目标位置，已知位置，递推顺序
     * @param coins
     * @param aim
     * @param idx
     * @return
     */
    public static int minAimCoin3(int[]coins,int aim,int idx) {
        int N=coins.length;
        int[][]dp=new int[N+1][aim+1];
        for (int i = 0; i < N+1; i++) {
            dp[i][0]=0;
        }
        for (int j = 1; j < aim+1; j++) {//j=1
            dp[N][j]=-1;
        }
        
        for (int i = N-1; i > -1; i--) {
            for (int j = 1; j < aim+1; j++) {
                int pNext=-1;//
                if(j-coins[i]>=0){
                    pNext=dp[i+1][j-coins[i]];
                }
                int qNext=dp[i+1][j];
                if(pNext==-1&&qNext==-1) {
                    dp[i][j]=-1;
                    continue;
                }
                if(pNext!=-1&&qNext==-1) {
                    dp[i][j]=pNext+1;
                    continue;
                }
                if(pNext==-1&&qNext!=-1) {
                    dp[i][j]=qNext;
                    continue;
                }

                dp[i][j]=Math.min(pNext+1, qNext);
            }
        }

        return dp[idx][aim];
    }

    static class Element{
        int[] coins;
        int aim;
    }
    public static Element getCoins(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] coins=new int[len];
        for (int i = 0; i < coins.length; i++) {
            int v=(int)(Math.random()*maxV);
            coins[i]=v;
        }

        int aim=(int)(Math.random()*maxV*2);
        Element element=new Element();
        element.coins=coins;
        element.aim=aim;
        return element;
    }

    public static void main(String[] args) {
        int times=100000;
        int maxLen=20;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            Element element=getCoins(maxLen, maxV);
            int min1=minAimCoin(element.coins, element.aim, 0);
            int min2=minAimCoin2(element.coins, element.aim, 0);
            int min3=minAimCoin3(element.coins, element.aim, 0);
            if(min1!=min2||min1!=min3||min2!=min3){
                System.out.println("not good");
                System.out.println(Arrays.toString(element.coins)+", aim="+element.aim);
                System.out.println(min1);
                System.out.println(min2);
                System.out.println(min3);
                throw new RuntimeException();
            }
        }
    }
}
