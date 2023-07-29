package com.xcrj.company.pass1;

import java.util.Arrays;

public class A070SMagicBlock {
    /**
     * 能够满足要求的最小几堆积木
     * 贪心
     * @param as as[积木]=重量
     * @param x 相连积木差值
     * @param k 魔法积木个数
     * @return
     */
    public static int splitBuildingBlock(int[]as,int x,int k){
        //第1个贪心：不使用魔法积木时形成几堆。获取堆数和相邻堆差值
        Arrays.sort(as);
        int n=as.length;
        int[] difs=new int[n];//相邻堆差值
        int size=0;//difs元素个数
        int num=1;//几堆
        for (int i = 1; i < n; i++) {
            //相邻积木差值>x需要形成新的堆
            if((as[i]-as[i-1])>x){
                num++;
                difs[size++]=as[i]-as[i-1];
            }
        }
        //是否只有1堆||要求x=0魔法积木无法介入||没有魔法积木也不需要进入下面贪心
        //x=0：魔法积木是用来弥补差值的，没有差值，有魔法积木也没用
        if(num==1||x==0||k==0){
            return num;
        }
        //第2个贪心：使用魔法积木先弥补小的差值
        Arrays.sort(difs,0,size);
        for (int i = 0; i < size; i++) {
            //需要几块魔法积木去弥补
            //例如，10 20，相差10，只需要15一块魔法积木
            int need=(difs[i]-1)/x;
            if(k>=need){
                num--;
                k-=need;
            }else break;
        }
        return num;
    }



    /**
     * 动态规划
     * 来到i，i和i+1只有三种选择，
     * 一，一定组成一堆
     * 二，分成新堆
     * 三，使用魔法积木组成一堆
     * @param as
     * @param x
     * @param i
     * @param r rest 剩余的魔法积木的数量
     * @return as[]能够满足要求的最小组成几堆积木
     */
    private static int f(int[] as, int x, int i, int r) {
        //三种情况，都是一堆，最终都来到这里，返回1堆
        if(i==as.length-1){
            return 1;
        }
        //i+1和i一定在一起，不需要分堆
        if(as[i+1]-as[i]<=x){
            return f(as,x,i+1,r);
        }else{
            //情况1，使用魔法积木尝试弥合两个堆
            int p1=Integer.MAX_VALUE;//r>=need不一定成立
            int need=(as[i+1]-as[i]-1)/x;
            if(r>=need){
                p1=f(as,x,i+1,r-need);
            }
            //情况2，分堆
            int p2=1+f(as,x,i+1,r);
            return Math.min(p1,p2);
        }
    }


}
