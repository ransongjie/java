package com.xcrj.basic.pass2.dp;

import java.util.Arrays;

/**
 * 组成目标值的硬币数量种数
 * 可重复选取同一个硬币(0~n次)
 * coins={硬币币值}
 * aim 目标值
 */
public class WayRAimCoin {
    /**
     * @param coins
     * @param rest
     * @param idx
     * @return
     */
    public static int wayRAimCoin(int[]coins,int rest,int idx) {
        if(rest<0) return 0;
        //遍历所有的硬币尝试组成rest
        if(idx==coins.length){
            return rest==0?1:0;
        }

        int r=0;
        //同一个硬币选取n次再去选取下一个
        for (int i = 0; i <= (rest/coins[idx]); i++) {//=
            r+=wayRAimCoin(coins, rest-coins[idx]*i, idx+1);
        }
        return r;
    }

    /**
     * 
     * @param coins
     * @param rest
     * @param idx
     * @return
     */
    public static int wayRAimCoin3(int[]coins,int aim,int idx) {
        int n=coins.length;
        int[][]dp=new int[n+1][aim+1];
        dp[n][0]=1;//第n行剩余位置为0

        for (int i = n-1; i >-1 ; i--) {
            for (int j = 0; j < aim+1; j++) {
                //枚举行为
                for (int k = 0; k <= (j/coins[i]); k++) {
                    dp[i][j]+=dp[i+1][j-coins[i]*k];
                }
            }
        }

        return dp[idx][aim];
    }

    public static int wayRAimCoin4(int[]coins,int aim,int idx) {
        int n=coins.length;
        int[][]dp=new int[n+1][aim+1];
        dp[n][0]=1;//第n行剩余位置为0

        for (int i = n-1; i >-1 ; i--) {
            for (int j = 0; j < aim+1; j++) {
                //优化枚举行为
                dp[i][j]=dp[i+1][j];
                if(j-coins[i]>=0){
                    dp[i][j]=dp[i][j-coins[i]]+dp[i+1][j];
                }
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
            int v=(int)(1+Math.random()*(maxV-1));
            coins[i]=v;
        }

        int aim=(int)(Math.random()*maxV*2);
        Element element=new Element();
        element.coins=coins;
        element.aim=aim;
        return element;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxLen=20;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getCoins(maxLen, maxV);
            int way1=wayRAimCoin(element.coins, element.aim, 0);
            int way3=wayRAimCoin3(element.coins, element.aim, 0);
            int way4=wayRAimCoin4(element.coins, element.aim, 0);
            if(way1!=way3||way3!=way4||way1!=way4){
                System.out.println("not good");
                System.out.println(Arrays.toString(element.coins)+", aim="+element.aim);
                System.out.println(way1);
                System.out.println(way3);
                System.out.println(way4);
                throw new RuntimeException();
            }
        }
    }
}
