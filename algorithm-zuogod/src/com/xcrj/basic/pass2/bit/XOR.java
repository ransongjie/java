package com.xcrj.basic.pass2.bit;

import java.util.Arrays;

/**
 * 异或
 * 无进位相加
 */
public class XOR {
    public static void main(String[] args) {
        System.out.println(2^0);//2
        System.out.println(2^2);//0

        //交换律
        System.out.println(2^3^4);//5
        System.out.println(3^2^4);//5

        //结合律
        System.out.println((2^3)^4);//5
        System.out.println(2^(3^4));//5

        //异或交换
        int[]as={1,2,3};
        swap(as,0,1);
        System.out.println(Arrays.toString(as));//{2,1,3}
    }

    public static void swap(int[]as,int a,int b) {
        as[a]=as[a]^as[b];
        as[b]=as[a]^as[b];
        as[a]=as[a]^as[b];
    }
}
