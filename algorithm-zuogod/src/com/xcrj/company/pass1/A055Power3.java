package com.xcrj.company.pass1;

/**
 * n是不是3的幂
 */
public class A055Power3 {
    /**
     * 1162261467是int型范围内，最大的3的幂，它是3的19次方
     * 1162261467 % n == 0 则n是3的幂。否则不是3的幂
     * @param n
     * @return
     */
    public static boolean power3(int n){
        return n>0 && 1162261467 % n == 0;
    }
}
