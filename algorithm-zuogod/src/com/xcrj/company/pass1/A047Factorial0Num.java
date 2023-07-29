package com.xcrj.company.pass1;

/**
 * n!末尾有几个0
 */
public class A047Factorial0Num {
    public static int factorial0Num(int n){
        int ans=0;
        while(n!=0){
            n/=5;
            ans+=n;
        }
        return ans;
    }
}
