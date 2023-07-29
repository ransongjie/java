package com.xcrj.basic.pass1.dp;

import java.util.Arrays;

/**
 * 组成目标值的最少硬币数量
 * coins={硬币币值}
 * aim 目标值
 */
public class MinAimCoin {
    public static void main(String[] args) {
        // int[] coins={2,4,7,3,5};
        // int aim=10;
        // System.out.println(minAimCoin3(coins, aim, idx));

        int times=100000;
        int maxLen=20;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            Element element=getCoins(maxLen, maxV);
            int min1=minAimCoin2(element.coins, element.aim, 0);
            int min2=minAimCoin3(element.coins, element.aim, 0);
            if(min1!=min2){
                System.out.println("not good");
                System.out.println(Arrays.toString(element.coins)+", aim="+element.aim);
                System.out.println(min1);
                System.out.println(min2);
                throw new RuntimeException();
            }
        }
    }

    /**
     * 暴力递归
     * 递归路径数=硬币数
     * 目标
     * 尝试：选择当前硬币或不选择当前硬币
     */
    public static int minAimCoin(int[]coins,int rest,int idx) {
        // if(idx==coins.length){
        //     return rest==0?1:0;
        // }
        if(rest<0) return -1;//非正常逻辑放到前面
        if(rest==0) return 0;//无硬币可选，所需硬币数量为0
        if(idx==coins.length) return -1;
        
        //选择当前硬币后, 再从idx+1~n选择k个硬币=rest-coins[idx]
        int cur=minAimCoin(coins, rest-coins[idx], idx+1);
        int nxt=minAimCoin(coins, rest, idx+1);
        if(cur==-1&&nxt==-1) return -1;
        if(cur==-1&&nxt!=-1) return nxt;
        if(cur!=-1&&nxt==-1) return cur+1;//1是当前硬币
        return Math.min(cur+1, nxt);
    }
    /**
     * 记忆搜索
     * 缓存大小
     * 初始状态
     * 命中缓存
     * 更新缓存
     */
    public static int minAimCoin2(int[]coins,int aim,int idx) {
        int[][] dp=new int[coins.length+1][aim+1];
        for (int[] is : dp) {
            Arrays.fill(is, -2);
        }
        return memSearch(coins, aim, idx, dp);
    }
    public static int memSearch(int[]coins,int rest,int idx,int[][]dp) {
        if(rest<0) return -1;//
        if(dp[idx][rest]!=-2) return dp[idx][rest];
        if(rest==0){
            dp[idx][rest]=0;
            return dp[idx][rest];   
        }
        if(idx==coins.length){
            dp[idx][rest]=-1;
            return dp[idx][rest];
        }

        int cur=memSearch(coins, rest-coins[idx], idx+1,dp);
        int nxt=memSearch(coins, rest, idx+1,dp);
        if(cur==-1&&nxt==-1) {
            dp[idx][rest]=-1;
            return dp[idx][rest];
        }
        if(cur==-1&&nxt!=-1) {
            dp[idx][rest]=nxt;
            return dp[idx][rest];
        }
        if(cur!=-1&&nxt==-1) {
            dp[idx][rest]=cur+1;
            return dp[idx][rest];
        }
        dp[idx][rest]=Math.min(cur+1, nxt);
        return dp[idx][rest];
    }
    /**
     * 动态规划
     * 拷贝暴力递归
     * dp大小
     * 目标位置
     * 已知状态
     * - 暴力递归base case
     * - 边界条件
     * 状态转移
     * - 可变参数数量=for循环数量
     * - 顺序
     * - 注意边界问题
     */
    public static int minAimCoin3(int[]coins,int rest,int idx) {//idx一直=0
        int N=coins.length;
        int[][] dp=new int[N+1][rest+1];
        // for (int[] is : dp) {
        //     Arrays.fill(is, -2);
        // }
        for (int i = 0; i < N+1; i++) {
            dp[i][0]=0;
        }
        for (int i = 1; i < rest+1; i++) {//
            dp[N][i]=-1;
        }
        
        for (int i = N-1; i >-1; i--) {//
            for (int j = 1; j < rest+1; j++) {
                int cur=-1;
                if(j-coins[i]>=0){//
                    cur=dp[i+1][j-coins[i]];
                }
                int nxt=dp[i+1][j];
                
                if(cur==-1&&nxt==-1) {
                    dp[i][j]=-1;
                    continue;
                }
                if(cur==-1&&nxt!=-1) {
                    dp[i][j]=nxt;
                    continue;
                }
                if(cur!=-1&&nxt==-1) {
                    dp[i][j]=cur+1;
                    continue;
                }

                dp[i][j]=Math.min(cur+1, nxt);
            }
        }

        return dp[idx][rest];//dp[0][aim] 来自记忆搜索
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
}
