package com.xcrj.basic.pass3.hash;

import com.sun.xml.internal.ws.resources.UtilMessages;

/**
 * 布隆过滤器
 */
public class Bloom {
    private static final int arrLen=10;//bitArr长度
    private static final int bitUnit=32;//int bit长度
    private static final int[] arr=new int[arrLen];

    /**
     * 查询第n位的状态
     * @param n
     * @return int
     */
    public static int getNbit(int n){
        int arrIdx=n/bitUnit;
        int bitIdx=n%bitUnit;
        return (arr[arrIdx]>>bitIdx)&1;
    }

    /**
     * 设置第n位为1
     * @param n
     */
    public static void setN1(int n){
        int arrIdx=n/bitUnit;
        int bitIdx=n%bitUnit;
        arr[arrIdx]|=(1<<bitIdx);
    }

    /**
     * 设置第n位为0
     * @param n
     */
    public static void setN0(int n){
        int arrIdx=n/bitUnit;
        int bitIdx=n%bitUnit;
        arr[arrIdx]&=(~(1<<bitIdx));
    }

    public static void main(String[] args) {
        int tims=10000,n,rand;
        for (int i = 0; i < tims; i++) {
            n=(int)(Math.random()*arrLen*bitUnit);
            rand=(int)(Math.random()*10);
            //偶，测试设置为1
            if((rand&1)==0){
                setN1(n);
                if((1&getNbit(n))==0){
                    throw new RuntimeException("err");
                }
            }
            //奇，测试设置为0
            else{
                setN0(n);
                if((1&getNbit(n))==1){
                    throw new RuntimeException("err");
                }
            }
        }
    }
}
