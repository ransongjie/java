package com.xcrj.company.pass1;

public class A031D1TrappingRain {
    public static int d1TrappingRain(int[] as) {
        //无法接水
        if (as == null || as.length < 3) {
            return 0;
        }

        int water = 0;
        int l = 1, r = as.length - 2;
        int lMax = as[0], rMax = as[as.length - 1];
        while (l <= r) {
            //木桶效应
            if (lMax <= rMax) {
                //相邻木块高度差是可以接住的水
                water += Math.max(0, lMax - as[l]);
                lMax = Math.max(lMax, as[l++]);
            } else {
                water += Math.max(0, rMax - as[r]);
                rMax = Math.max(rMax, as[r--]);
            }
        }

        return water;
    }
}
