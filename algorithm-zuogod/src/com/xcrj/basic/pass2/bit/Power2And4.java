package com.xcrj.basic.pass2.bit;
/**
 * 2或4的幂
 */
public class Power2And4 {
    public static void main(String[] args) {
        int times=1000000;
        int maxV=10000;
        for (int i = 0; i <times; i++) {
            int x=getX(maxV);
            boolean bool21=cpPower2(x);
            boolean bool22=power2(x);
            if(bool21!=bool22){
                System.out.println("not good 2");
                System.out.println(x);
                System.out.println(bool21);
                System.out.println(bool22);
                throw new RuntimeException();
            }

            boolean bool41=cpPower4(x);
            boolean bool42=power4(x);
            if(bool41!=bool42){
                System.out.println("not good 4");
                System.out.println(x);
                System.out.println(bool41);
                System.out.println(bool42);
                throw new RuntimeException();
            }
        }
    }

    /**
     * mostRight1等于a
     * @param a
     * @return
     */
    public static boolean power2(int a) {
        int r1=a&(~a+1);
        return r1==a;
    }

    /**
     * a&(a-1)等于0
     * @param a
     * @return
     */
    public static boolean power2b(int a) {
        int a_1=a-1;
        return (a&a_1)==0;
    }

    /**
     * 首先是2的幂
     * 其次a&(0x55555555)!=0。仅有的一个1在奇数位上
     * @param a
     * @return
     */
    public static boolean power4(int a) {
        if(!power2(a)) return false;
        return (a&0x55555555)!=0;
    }

    public static boolean cpPower2(int x) {
        for (int i = 0; i < 31; i++) {
            if(x==(1<<i)) return true;
        }
        return false;
    }

    public static boolean cpPower4(int x) {
        for (int i = 0; i < 16; i++) {
            if(x==(1<<(i*2))) return true;
        }
        return false;
    }

    /**
     * 正数
     * @param maxV
     * @return
     */
    public static int getX(int maxV) {
        int v=(int)(1+Math.random()*(maxV-1));
        return v;
    }
}
