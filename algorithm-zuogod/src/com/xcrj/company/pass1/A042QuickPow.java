package com.xcrj.company.pass1;

/**
 * a^b b非负
 */
public class A042QuickPow {
    //a^b
    public static int quickPow(int a,int b){
        int t=a;
        int ans=1;
        while(b!=0){
            if((b&1)!=0){
                ans*=t;
            }
            t*=t;
            b>>=1;
        }

        return ans;
    }

    public static void main(String[] args) {
        quickPow(2,4);
        System.out.println(quickPow(2,2));
    }
}
