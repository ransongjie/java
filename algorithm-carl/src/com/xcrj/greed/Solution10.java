package com.xcrj.greed;

/*
 * https://leetcode.cn/problems/lemonade-change/
 * 柠檬水找零
 */
public class Solution10{
    /*
     * 贪心：因为美元10只能给账单20找零，而美元5可以给账单10和账单20找零，美元5更万能！
     */
    public boolean lemonadeChange(int[] bills) {
        int five=0,ten=0,twenty=0;
        for(int bill:bills){
            if(bill==5){
                five++;
                continue;
            }
            if(bill==10){
                if(five<1) return false;
                ten++;
                five--;
                continue;
            }
            if(bill==20){
                if(ten>0&&five>0){
                    ten--;
                    five--;
                }else if(five>=3){
                    five-=3;
                }else return false;
                continue;
            }
        }
        return true;
    }
}