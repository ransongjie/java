package com.xcrj.basic.pass1.bit;
//异或 无进位相加
public class Yihuo {
    public static void main(String[] args) {
        basicRule();
        swapByXOR();
    }

    public static void basicRule(){
        //0^n=n;
        System.out.println(0^6);
        //n^n=0;
        System.out.println(6^6);
        //交换律
        System.out.println(7^8^9);
        System.out.println(7^9^8);
        //结合律
        System.out.println(7^(9^8)^3);
        System.out.println((7^9)^8^3);
    }

    public static void swapByXOR(){
        int a=23;
        int b=24;
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println(a);//24
        System.out.println(b);//23
    }


}
