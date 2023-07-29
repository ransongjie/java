package com.xcrj.basic.pass1.test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        //数组比较
        int[] as={1,2,3};
        int[] bs={1,2,3};
        
        if(Arrays.equals(as, bs)){
            System.out.println("good");
        }
    }
}
