package com.xcrj.basic.pass1.force;
//纸牌数组，两个玩家，开头或末尾拿牌，获胜者分数
public class CardPlyer {
    public static void main(String[] args) {
        int[]cards={1,2,3,4,5,6};
        //两种情况 我先手或者后手
        int max=Math.max(first(cards, 0, cards.length-1), 
                        second(cards, 0, cards.length-1));
        System.out.println(max);
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
}
