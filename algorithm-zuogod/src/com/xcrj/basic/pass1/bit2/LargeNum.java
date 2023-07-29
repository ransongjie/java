package com.xcrj.basic.pass1.bit2;
//返回更大的值 32位有符号数 不能使用比较运算符
public class LargeNum {
    public static void main(String[] args) {
        // int a=3,b=-8;
        // // System.out.println(largeNum(a, b));
        // System.out.println(largeNum2(a, b));

        int times=100000;
        int maxV=100;
        int minV=-300;
        for (int i = 0; i < times; i++) {
            Element e=getE(minV, maxV);
            int large1=cp(e.a, e.b);
            int large2=largeNum2(e.a, e.b);
            if(large1!=large2){
                System.out.println("not good");
                System.out.println(e.a+", "+e.b);
                System.out.println(large1);
                System.out.println(large2);
            }
        }
    }

    //0 1互斥代表 ifelse。都是正整数
    public static int largeNum(int a,int b) {
        int c=a-b;
        //构建01互斥
        int sca=signNum(c);
        int scb=flip(sca);
        return sca*a+scb*b;
    }
    //>=0 return 1, <0 return 0
    private static int signNum(int x) {
        return flip((x>>31)&1);
    }
    //异或都是1返回0，翻转0, 1
    private static int flip(int x) {
        // return (~x+1);
        return x^1;
    }
    //整数 , 倒着编程
    public static int largeNum2(int a,int b) {
        int c=a-b;
        int sc=signNum(c);
        int sa=signNum(a);
        int sb=signNum(b);
        int diffab=sa^sb;//ab符号不同应该为1，相同为0
        int sameab=flip(diffab);
        int returna=diffab*sa+sameab*sc;//10互斥条件，a b符号不同时取决于a的符号，相同时取决于c的符号
        int returnb=flip(returna);
        return returna*a+returnb*b;//10互斥条件 要么返回a要么返回b
    }

    public static int cp(int a,int b) {
        return a>=b?a:b;
    }
    static class Element{
        int a;
        int b;
    }
    public static Element getE(int minV,int maxV) {
        int a=(int)(minV+Math.random()*(maxV-minV));
        int b=(int)(minV+Math.random()*(maxV-minV));
        Element e=new Element();
        e.a=a;
        e.b=b;
        return e;
    }
}
