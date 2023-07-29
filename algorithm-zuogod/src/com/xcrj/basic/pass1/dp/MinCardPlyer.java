package com.xcrj.basic.pass1.dp;

import java.util.Arrays;

/**
 * 纸牌数组，两个玩家，开头或末尾拿牌，获胜者分数
 * cards={1,2,3,4,5,6}
 */
public class MinCardPlyer {
    public static void main(String[] args) {
        // int[]cards={1,2,3,4,5,6};
        // System.out.println(cardPlyer(cards));
        // System.out.println(cardPlyer3(cards));

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
            }
        }
    }
    
    /**
     * 暴力递归
     * @param cards
     * @return
     */
    public static int cardPlyer(int[]cards) {
        if(cards==null||cards.length==0) return 0;
                //两种情况 我先手或者后手
        return Math.max(first(cards, 0, cards.length-1), 
                        second(cards, 0, cards.length-1));
    }

    //先手
    public static int first(int[]cards,int l,int r) {
        if(l==r)return cards[l];//最后一张
        //不是最后一张，我的先手跟着别人的后手
        return Math.max(cards[l]+second(cards, l+1, r), 
                        cards[r]+second(cards, l, r-1));
    }

    //后手
    public static int second(int[]cards,int l,int r) {
        if(l==r)return 0;//最后一张被别人先手拿走
        //不是最后一张，我的后手（没得拿）跟着别人的先手
        return Math.min(first(cards, l+1, r), first(cards, l, r-1));
    }

    /**
     * 需要两个dp数组
     * 动态规划
     * 拷贝暴力递归
     * dp大小 
     * 目标位置
     * 已知状态
     * 状态转移
     */ 
    public static int cardPlyer3(int[]cards) {
        if(cards==null||cards.length==0) return 0;

        int n=cards.length;
        int[][]fdp=new int[n][n];
        int[][]sdp=new int[n][n];
        for (int i = 0; i < n; i++) {
            fdp[i][i]=cards[i];
            sdp[i][i]=0;
        }

        for (int k = 1; k < n; k++) {
            for (int i = 0,j=k; i < n-1&&j<n; i++,j++) {
                fdp[i][j]=Math.max(cards[i]+sdp[i+1][j], cards[j]+sdp[i][j-1]);
                sdp[i][j]=Math.min(fdp[i+1][j], fdp[i][j-1]);
            }
        }
        
        return Math.max(fdp[0][n-1], sdp[0][n-1]);
    }

    public static int[] getCards(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
