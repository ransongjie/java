package com.xcrj.company.pass1;

public class A049CountPrimes {
    public static int countPrimes(int n){
        if(n<3){//1不是质数
            return 0;
        }
        //动态规划
        boolean[] notPrime=new boolean[n];
        //排除偶数项目，偶数一定不是素数 质数
        int cnt=n/2;
        // 3 5 7 9... 判断是不是素数
        for (int i = 3; i*i < n; i+=2) {
            //已知不是素数 继续
            if(notPrime[i]){
                continue;
            }
            //3-> 3*3=9是不是素数 3*5=3*3+3*2=15 3*7=3*5+3*2=21
            //5-> 5*5=25是不是素数 5*7=5*5+5*2=35 5*9=5*5+5*2=45
            for (int j = i*i; j < n; j+=i*2) {
                if(!notPrime[j]){
                    cnt--;
                    notPrime[j]=true;
                }
            }
        }

        return cnt;
    }
}
