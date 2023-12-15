package com.xcrj.basic.pass2.hash;

public class Bloom {
    public static void main(String[] args) {
        int times=10000;
        int bitMapSize=num*bitNum;
        Bloom bloom=new Bloom();
        for (int j = 0; j < times; j++) {
            double m=Math.random();
            int n=(int)(Math.random()*bitMapSize);
            if(m>0.5){
                bloom.setBitN1(n);
                if(1!=bloom.getBitN(n)){
                    throw new RuntimeException();
                }
            }else{
                bloom.setBitN0(n);
                if(0!=bloom.getBitN(n)){
                    throw new RuntimeException();
                }
            }
        }
    }

    static final int num=10;
    static final int bitNum=32;//int占用32bit
    static final int[]bitMap=new int[num];//num*bitNum

    public int getBitN(int n) {
        //先求在哪一个int
        int arrIdx=n/bitNum;
        //再求在哪一个bit
        int bitIdx=n%bitNum;
        return (bitMap[arrIdx]>>bitIdx)&1;
    }

    public void setBitN1(int n) {
        int arrIdx=n/bitNum;
        int bitIdx=n%bitNum;
        //|=1<<bitIdx
        bitMap[arrIdx]|=1<<bitIdx;
    }

    public void setBitN0(int n) {
        int arrIdx=n/bitNum;
        int bitIdx=n%bitNum;
        //&=(~(1<<bitIdx))
        bitMap[arrIdx]&=(~(1<<bitIdx));
    }
}
