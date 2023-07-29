package com.xcrj.company.pass1;

public class A044Sqrt {
    //结果向下取整
    public static int sqrt(int x){
        if(x==1) return x;
        if(x<3) return 1;
        long ans=1;
        long l=1,r=x,mid;
        while (l<=r){
            mid=(l+r)>>1;
            if(mid*mid<=x){
                ans=mid;// 向下取整 记录左接近x的mid
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return (int)ans;
    }
}
