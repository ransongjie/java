package com.xcrj.basic.pass2.force;
/**
 * 纸牌数组，两个玩家，开头或末尾拿牌，获胜者分数
 */
public class CardPlyer {
    public static void main(String[] args) {
        int[]cards={1,2,3,4,5,6};
        //两种情况 我先手或者后手
        int max=Math.max(first(cards, 0, cards.length-1), 
                        second(cards, 0, cards.length-1));
        System.out.println(max);
    }

    public static int first(int[] cards,int left,int right) {
        if(left==right) return cards[left];
        return Math.max(cards[left]+second(cards, left+1, right),
                        cards[right]+second(cards, left, right-1));
    }

    public static int second(int[] cards,int left,int right) {
        if(left==right) return 0;
        return Math.min(first(cards, left+1, right),first(cards, left, right-1));
    }
}
