package com.xcrj.company.pass1;

import java.util.Arrays;

public class A071MinRadiusHeaterHouse {
    public static int minRadiusHeaterHouse(int[]houses,int[]heaters){
        int ans=0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        //求heaters[j]是houses[i]的最优供暖点吗
        for (int i = 0,j=0; i <houses.length ; i++) {
            //heaters[j]不是houses[i]的最优供暖点，j++看下一个供暖点
            while (!bestHeater(houses,i,heaters,j)){
                j++;
            }
            //heaters[j]是houses[i]的最优供暖点，更新统一供暖半径
            ans=Math.max(ans,Math.abs(heaters[j]-houses[i]));
        }
        return ans;
    }

    /**
     * heaters[j]是houses[i]的最优供暖点吗
     * @param houses
     * @param i
     * @param heaters
     * @param j
     * @return true j是i最优供暖点
     */
    private static boolean bestHeater(int[]houses,int i,int[]heaters,int j){
        //第j供暖点必须更小，等于也不行
        return j==heaters.length-1||Math.abs(heaters[j]-houses[i])<Math.abs(heaters[j+1]-houses[i]);
    }
}
