package com.xcrj.basic.pass1.bit2;

//加减乘除 32位有符号数。使用常规四则运算会溢出的输入，这里照样会溢出
public class AddSubMulDiv {
    public static void main(String[] args) {
        int a=Integer.MIN_VALUE, b=-2;
        // System.out.println(add(a, b));
        // System.out.println(sub(a, b));
        // System.out.println(mul(a, b));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(div(a, b));
        // System.out.println(divide(a, b));
    }
    //无进位相加+进位信息
    //无进位信息则 返回 无进位相加结果
    public static int add(int a,int b) {
        int a1=(a^b);
        int b1=((a&b)<<1);
        while(b1!=0){
            a=(a1^b1);
            b=((a1&b1)<<1);
            a1=a;
            b1=b;
        }
        return a1;
    }

    public static int sub(int a,int b) {
        return add(a, neg(b));
    }

    private static int neg(int x){
        return add(~x,1);//
    }

    public static int mul(int a,int b) {
        int r=0;
        while(b!=0){
            if((b&(neg(b)))==1){
                r=add(r, a);
            }
            a<<=1;
            b>>>=1;
        }
        return r;
    }
    
    public static int div(int a,int b) {
        if(b==0){
            throw new IllegalArgumentException("b is 0");
        }
        if(a==Integer.MIN_VALUE&&b==Integer.MIN_VALUE) return 1;
        if(a!=Integer.MIN_VALUE&&b==Integer.MIN_VALUE) return 0;
        //divide中是转为正数计算的, -Integer.MIN_VALUE 将越界溢出
        if(a==Integer.MIN_VALUE&&b!=Integer.MIN_VALUE) {//todo
            int m1=divide((add(a, 1)), b);
            int m2=mul(m1, b);
            int m3=sub(a, m2);
            int m4=div(m3, b);
            return add(m4, m1);//a=Integer.MIN_VALUE, b=-2 r=1073741824
            // return add(div(-1,b),m1);//a=Integer.MIN_VALUE, b=-2 r=1073741823
        }
        return divide(a,b);
    }

    public static int divide(int x,int y) {
        //全转为正数
        int a=x<0?neg(x):x;
        int b=y<0?neg(y):y;

        int r=0;
        for (int i = 31; i > -1; i=sub(i, 1)) {
            if((a>>i)>=b){//b尽可能左移
                a=sub(a, b<<i);
                r|=(1<<i);//商
            }
        }

        // 有且只有1个负数时，结果为负。否则结果为正
        return (x<0)^(y<0)?neg(r):r;
    }
}
