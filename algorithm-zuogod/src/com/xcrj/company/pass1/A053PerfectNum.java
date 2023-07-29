package com.xcrj.company.pass1;

public class A053PerfectNum {
    public static int perfectNum(int n){
        //任何数消掉4的因子，仍然满足 4平方和定义
        while(n%4==0){//去掉因子4，n/4的余数为0，含有因子4
            n/=4;
        }
        //4平方和
        if(n%8==7){
            return 4;
        }
        //寻找1或2平方和
        for (int a = 0; a*a < n; a++) {
            //强制拆成2平方和
            int b=(int)Math.sqrt(n-a*a);
            if(a*a+b*b==n){
                return (a==0||b==0)?1:2;
            }
        }

        //只能是3平方和
        return 3;
    }
}
