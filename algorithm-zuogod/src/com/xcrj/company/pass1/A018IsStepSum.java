package com.xcrj.company.pass1;

/**
 * stepSum是不是每个数的步骤和
 * 680+68+6=754, 754是680的步骤和
 *
 * 二分法
 */
public class A018IsStepSum {
    public static boolean isStepSum(int stepSum){
        int l=0,r=stepSum,m=0,ms=0;
        while(l<=r){
            m=(l+r)>>1;
            ms=stepSum(m);
            if (ms==stepSum) {
                return true;
            }else if(ms>stepSum){
                r=m-1;
            }else{
                l=m+1;
            }
        }
        return false;
    }

    //num/10
    private static int stepSum(int num){
        int sum=0;
        while(num!=0){//
            sum+=num;
            num/=10;
        }
        return sum;
    }
}
