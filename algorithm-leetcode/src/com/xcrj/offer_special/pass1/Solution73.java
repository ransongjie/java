package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 073. 狒狒吃香蕉
 * 狒狒喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 狒狒可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。
 * 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 分析
 * - 求狒狒在h小时内能够吃完所有香蕉的最小速度k
 * - piles[]，len为香蕉堆数，下标为第i堆香蕉，值为第i堆有多少根香蕉
 * - 1小时最多只能吃1堆香蕉
 */
public class Solution73 {
    /**
     * 二分法
     */
    public int minEatingSpeed(int[] piles, int h) {
        /**
         *  l是吃香蕉的最小速度
         *  r是吃香蕉的最大速度
         *  l=1，因为狒狒1h至少吃一根香蕉
         */
        int lSpeed = 1;
        // r=Max(piles)，因为狒狒1h最多吃一堆香蕉，所以需要求piles[]中的最大值
        int rSpeed = 0;
        for (int pile : piles) {
            rSpeed = Math.max(rSpeed, pile);
        }

        // 二分法查找 速度k/1h 在h小时内能够吃完所有香蕉的最小速度k
        int k = rSpeed;
        while (lSpeed < rSpeed) {
            int midSpeed = ((rSpeed - lSpeed) >> 1) + lSpeed;
            long timeSum = getTime(piles, midSpeed);
            // 若吃完所有香蕉实际花费总时间<给定时间，则减小速度，则速度靠左边移动
            // 为什么能求最小速度k，因为进入这个if后速度向更低速度折半
            if (timeSum <= h) {
                // 求最小速度k，所以k放到这
                k = midSpeed;
                rSpeed = midSpeed;
            }
            // 若吃完所有香蕉实际花费总时间>给定时间，则增大速度，则速度靠右边移动
            else {
                lSpeed = midSpeed + 1;
            }
        }

        return k;
    }

    /**
     * 狒狒以速度speed吃完成所有香蕉花费的时间
     * 注意：1堆里面至少有1根香蕉，因此狒狒吃香蕉的最小速度是 1根/h 1根1小时
     *
     * @param piles 所有堆香蕉
     * @param speed 狒狒吃香蕉的速度 根数/h 1小时吃多少根香蕉
     */
    public long getTime(int[] piles, int speed) {
        long timeSum = 0;
        for (int pile : piles) {
            /**
             *  1小时最多只能吃1堆香蕉
             *  - (pile) / speed, 向上取整 =(pile + speed) / speed
             *
             *  1堆香蕉至少有1根香蕉，狒狒1小时至少吃1根香蕉
             *  - (pile + speed - 1) / speed，因为pile和speed都>0
             *  例如，当pile=1，speed等于1时，(pile + speed) / speed=2，(pile + speed - 1) / speed=1
             */
            timeSum += (pile + speed - 1) / speed;
        }

        return timeSum;
    }
}
