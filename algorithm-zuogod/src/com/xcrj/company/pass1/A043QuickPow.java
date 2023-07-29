package com.xcrj.company.pass1;

public class A043QuickPow {
    //n可以为负数
    public static double quickPow(double x,int n){
        double t=x;
        double ans=1.0;
        int b=Math.abs(n==Integer.MIN_VALUE?n+1:n);
        while(b!=0){
            if((b&1)==1){
                ans*=t;
            }
            t*=t;
            b>>=1;
        }
        if(n==Integer.MIN_VALUE){
            ans*=x;
        }
        return n<0?1.0/ans:ans;//! 类型一致 进行运算
    }
}
