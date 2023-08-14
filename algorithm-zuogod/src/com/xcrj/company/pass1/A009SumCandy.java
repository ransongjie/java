package com.xcrj.company.pass1;

import java.util.Arrays;

/**
 * 孩子分糖果所需最少糖果数量
 */
public class A009SumCandy {
    public static int candy(int[]as){
        //正着一遍
        int[]ls=new int[as.length];
        Arrays.fill(ls,1);//每个小孩至少1颗糖
        for (int i = 1; i < ls.length; i++) {
            if(as[i]>as[i-1]){//后一个小孩成绩更高糖果数+1
                ls[i]=ls[i-1]+1;
            }
        }

        //反着一遍
        int[]rs=new int[as.length];
        Arrays.fill(rs,1);//每个小孩至少1颗糖
        for (int i = rs.length-2; i >=0; i--) {
            if(as[i]>as[i+1]){//
                rs[i]=rs[i+1]+1;
            }
        }

        //取得每个小孩最小值，再求和
        int ans=0;
        for (int i = 0; i < as.length; i++) {
            ans+=Math.max(ls[i],rs[i]);
        }
        return ans;
    }
}
