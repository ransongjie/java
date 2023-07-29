package com.xcrj.basic.pass1.force;

//汉诺塔
public class Hanoi{
    public static void main(String[] args) {
        hanoi(3, "左", "右", "中");
    }
    public static void hanoi(int n,String from,String to,String other) {
        if(n==1){
            System.out.println(n+": "+from+", "+to);
        }else{
            hanoi(n-1, from, other, to);
            System.out.println(n+": "+from+", "+to);
            hanoi(n-1, other, to, from);
        }
    }
}