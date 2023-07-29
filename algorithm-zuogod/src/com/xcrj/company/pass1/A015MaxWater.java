package com.xcrj.company.pass1;

/**
 * 二维能围住的最大水量
 */
public class A015MaxWater {
    //O(n)
    public static int maxWater(int[]as){
        int ans=0;
        int l=0,r=as.length-1;
        while(l<r){
            ans=Math.max(ans,Math.min(as[l],as[r])*(r-l));
            if(as[l]<as[r]){
                l++;
            }else{
                r--;
            }
        }

        return ans;
    }
}
