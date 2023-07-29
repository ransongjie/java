package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        // int[] as={162,13,21,11};
        // radixSort(as);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getPositiveAs(maxLen, maxV);
            int[]as1=Arrays.copyOf(as, as.length);
            int[]as2=Arrays.copyOf(as, as.length);

            cp(as1);
            radixSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
            }
        }
    }

    /**
     * 求所有数的最大位
     * 遍历每个数的每一位放到cnts中
     * 倒序原数组根据cnts放到help数组中
     * 将值从help数组拷贝回原数组
     * @param as
     */
    public static void radixSort(int[]as) {
        int maxK=0;//
        for (int a : as) {
            int k=getK(a);
            if(maxK<k){
                maxK=k;
            }
        }

        //依次处理每个数的第0位，第1位，第2位
        for (int i = 0; i < maxK; i++) {
            int[]cnts=new int[10];//第i位为idx的数的数量
            for (int a : as) {
                int v=getiV(a, i);
                cnts[v]++;
            }

            //累加, 第i位>=idx的数的数量
            for (int j = 1; j < cnts.length; j++) {
                cnts[j]+=cnts[j-1];
            }

            //倒序到help
            int[] help=new int[as.length];
            for (int j = as.length-1; j > -1; j--) {
                int v=getiV(as[j], i);
                int idx=cnts[v]-1;
                help[idx]=as[j];
                cnts[v]--;
            }

            //到as
            System.arraycopy(help, 0, as, 0, as.length);
        }
    }

    public static int getK(int a) {
        int k=0;//
        while(a!=0){
            k++;
            a/=10;
        }
        return k;
    }

    //获取a第i位的值
    public static int getiV(int a,int i) {
        int k=-1;//
        int tmp=a;
        while(tmp!=0){
            k++;
            tmp/=10;
        }
        if(i>k) return 0;

        //
        for (int j = 0; j < i; j++) {
            a/=10;
        }
        return a%10;
    }

    public static int[] getPositiveAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            while(v<0){
                v=(int)(Math.random()*maxV);
            }
            as[i]=v;
        }
        return as;
    }

    public static void cp(int[] as) {
        Arrays.sort(as);
    }
}
