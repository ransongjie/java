package com.xcrj.basic.pass1.bit2;
//2的幂，4的幂。32位正数
public class Power24 {
    public static void main(String[] args) {
        int times=1000000;
        int maxV=10000;
        for (int i = 0; i <times; i++) {
            int x=getX(maxV);
            boolean bool21=cpPower2(x);
            boolean bool22=power2(x);
            if(bool21!=bool22){
                System.out.println("not good 2");
                System.out.println(x);
                System.out.println(bool21);
                System.out.println(bool22);
                throw new RuntimeException();
            }

            boolean bool41=cpPower4(x);
            boolean bool42=power4(x);
            if(bool41!=bool42){
                System.out.println("not good 4");
                System.out.println(x);
                System.out.println(bool41);
                System.out.println(bool42);
                throw new RuntimeException();
            }
        }
    }
    //x的二进制表示中只有1位是1
    public static boolean power2(int x) {
        int mostRight1=x&(~x+1);
        return mostRight1==x;
    }

    //x-1, mostRight1之后全是1
    public static boolean power2t(int x) {
        return (x&(x-1))==0;
    }

    //首先是2的幂（也只有1个1），其次1只能出现在奇数位上（1的位置有限制）
    public static boolean power4(int x) {
        if(!power2(x)) return false;
        return (x&(0x55555555))!=0;//4bit描述一个只能出现在奇数位上
    }
    //正数
    public static int getX(int maxV) {
        int v=(int)(1+Math.random()*(maxV-1));
        return v;
    }

    public static boolean cpPower2(int x) {
        for (int i = 0; i < 31; i++) {
            if(x==(1<<i)) return true;
        }
        return false;
    }

    public static boolean cpPower4(int x) {
        for (int i = 0; i < 16; i++) {
            if(x==(1<<(i*2))) return true;
        }
        return false;
    }
}
