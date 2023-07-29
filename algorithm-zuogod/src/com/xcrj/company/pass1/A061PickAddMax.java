package com.xcrj.company.pass1;

import java.util.Arrays;

public class A061PickAddMax {
    public static int pickAddMax(int[]as){
        int ans=0;
        Arrays.sort(as);
        for (int i = as.length-1; i >=0 ; i--) {
            //公式
            ans=(ans<<1)+as[i];
        }
        return ans;
    }
}
