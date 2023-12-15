package com.xcrj.basic.pass2.dp;

import java.util.Arrays;

/**
 * 纸牌数组，两个玩家，开头或末尾拿牌，获胜者分数
 * cards={1,2,3,4,5,6}
 */
public class MinCardPlyer {
    public static int cardPlyer(int[]cards) {
        //先后手都尝试 获取最大值
        return Math.max(first(cards, 0, cards.length-1), 
        second(cards, 0, cards.length-1));
    }

    private static int first(int[]cards,int l,int r) {
        if(l==r) return cards[l];
        return Math.max(cards[l]+second(cards, l+1, r), 
        cards[r]+second(cards, l, r-1));
    }

    private static int second(int[]cards,int l,int r) {
        if(l==r) return 0;
        //我后手无牌可拿
        return Math.min(first(cards, l+1, r), 
        first(cards, l, r-1));
    }

    /**
     * 动态规划
     * @param cards
     * @return
     */
    public static int cardPlyer3(int[]cards) {
        int n=cards.length;
        // dp[次数][第i张牌]
        int[][]first=new int[n][n];
        int[][]second=new int[n][n];
        for (int i = 0; i < n; i++) {
            // 第i次先手拿第i张牌
            first[i][i]=cards[i];
            second[i][i]=0;
        }
        //走斜线
        for (int k = 1; k < n; k++) {//第几条斜线
            for (int i=0, j = k; i<n-1 &&j < n; i++,j++) {
                first[i][j]=Math.max(cards[i]+second[i+1][j], cards[j]+second[i][j-1]);
                second[i][j]=Math.min(first[i+1][j],first[i][j-1]);
            }
        }
        return Math.max(first[0][n-1],second[0][n-1]);
    }

    public static int[] getCards(int maxLen,int maxV) {
        int len=(int)(Math.random()*(maxLen-1)+1);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxLen=10;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] cards=getCards(maxLen, maxV);
            int max1=cardPlyer(cards);
            int max2=cardPlyer3(cards);
            if(max1!=max2){
                System.out.println("not good");
                System.out.println(Arrays.toString(cards));
                System.out.println(max1);
                System.out.println(max2);
                throw new RuntimeException();
            }
        }
    }
}
