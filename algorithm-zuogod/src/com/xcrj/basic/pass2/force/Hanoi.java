package com.xcrj.basic.pass2.force;
/**
 * 汉诺塔
 * 只有1个
 * - from 到 to
 * 不只有1个
 * - 先把n-1个 from到other
 * - 把第n个 from到to
 * - 再把n-1个 other到to
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, "左", "右", "中");
    }

    public static void hanoi(int n,String from,String to,String other) {
        if(n==1){
            System.out.println(n+":"+from+"->"+to);
        }else{
            hanoi(n-1, from, other, to);
            System.out.println(n+":"+from+"->"+to);
            hanoi(n-1, other, to, from);
        }
    }
}
