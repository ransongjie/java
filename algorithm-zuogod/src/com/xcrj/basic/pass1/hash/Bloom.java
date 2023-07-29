package com.xcrj.basic.pass1.hash;
//布隆过滤器
public class Bloom {
    public static void main(String[] args) {
        int times=10000;
        int setBit01=1000;
        for (int i = 0; i < times; i++) {
            int n=(int)(Math.random()*(arrLen*bitUnit));
            int m=(int)(Math.random()*(setBit01));
            if(m%2==0){//设置为1
                setBitN1(n);
                if(1!=getBitN(n)){
                    System.out.println("not good");
                }
            }else{
                setBitN0(n);
                if(0!=getBitN(n)){
                    System.out.println("not good");
                }
            }
        }
    }
    
    static final int arrLen = 10;//int[]长度
    static final int bitUnit= 32;//int 4个字节共32bit
    static int[] arr=new int[arrLen];//32bit*10=320bit

    //查询第n个bit的状态
    public static int getBitN(int n) {
        int arrIdx=n/bitUnit;//数组索引
        int bitIdx=n%bitUnit;//int 32bit内部索引
        return (arr[arrIdx]>>bitIdx)&1;
    }

    //设置第n个bit的状态为1
    public static void setBitN1(int n) {
        int arrIdx=n/bitUnit;//数组索引
        int bitIdx=n%bitUnit;//int 32bit内部索引
        arr[arrIdx]|=(1<<bitIdx);
    }

    //设置第n个bit的状态为0
    public static void setBitN0(int n) {
        int arrIdx=n/bitUnit;//数组索引
        int bitIdx=n%bitUnit;//int 32bit内部索引
        arr[arrIdx]&=(~(1<<bitIdx));
    }
}
