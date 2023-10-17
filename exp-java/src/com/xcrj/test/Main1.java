package com.xcrj.test;

import java.util.concurrent.*;

/**
 * FutureTask
 */
public class Main1 {
    public static void main(String[] args) {
        Integer total=128;
        test1(total);
        System.out.println(total);//128
    }

    private static void test1(Integer total){
        if(total<1){
            total+=1;
        }
        total+=10;
    }

}
