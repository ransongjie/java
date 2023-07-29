package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 073. 狒狒吃香蕉
 * - 求狒狒在h小时内能够吃完所有香蕉的最小速度k，k根/1小时
 * - 1小时最多只能吃1堆香蕉，哪怕香蕉数量<狒狒吃香蕉的速度
 * - 1小时至少吃1根香蕉，在一堆香蕉只有1根香蕉时
 */
public class Solution73 {
    /**
     * 二分法逼近最小达标速度
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 赋值为最小速度。狒狒必须要吃香蕉，至少吃一根
        int lspeed=1;
        // 赋值为最大速度。最大堆
        int rspeed=0;
        for(int pile:piles){
            rspeed=Math.max(pile, rspeed);
        }

        // xcrj 二分法逼近最小达标速度
        int k=rspeed;
        while(lspeed<rspeed){
            int midspeed=(lspeed+rspeed)>>1;
            long timesum=getTime(piles, midspeed);
            // xcrj 时间花费更小，减小速度，增大时间
            if(timesum<=h){
                k=midspeed;
                rspeed=midspeed;
            }
            // xcrj 时间花费更大，增大速度，减小时间
            else{
                lspeed=midspeed+1;
            }
        }
        return k;
    }

    /**
     * 以speed吃完所有香蕉的速度
     * @param piles
     * @param speed
     * @return
     */
    public long getTime(int[] piles, int speed) {
        long timesum=0;
        for(int pile:piles){
            // 特殊情况，某堆香蕉只有1根，pile=1, speed=1, timesum=1
            timesum+=(pile+speed-1)/speed;
        }
        return timesum;
    }
}
