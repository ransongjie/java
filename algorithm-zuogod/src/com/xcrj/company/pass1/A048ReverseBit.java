package com.xcrj.company.pass1;

public class A048ReverseBit {
    public static int reverseBit(int n){
        //高低16位交换
        n=(n>>>16)|(n<<16);
        //高8位交换
        n=((n&0xff00ff00)>>>8)|((n&0x00ff00ff)<<8);
        //高4位交换 11110000=f0  00001111=0f
        n=((n&0xf0f0f0f0)>>>4)|((n&0x0f0f0f0f)<<4);
        //高2位交换 1100=c   0011=3
        n=((n&0xcccccccc)>>>2)|((n&0x33333333)<<2);
        //高1位交换 1010=a 0101=5
        n=((n&0xaaaaaaaa)>>>1)|((n&0x55555555)<<1);

        return n;
    }
}
