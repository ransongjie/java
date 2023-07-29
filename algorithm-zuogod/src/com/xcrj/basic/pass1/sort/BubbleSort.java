package com.xcrj.basic.pass1.sort;

import java.util.Arrays;
// 每次都可能交换
public class BubbleSort {
    public static void main(String[] args) {
        int times=10000;
        int maxLen=1000;
        int maxV=100;
        int[] as=getAs(maxLen,maxV);
        for(int i=0;i<times;i++){
            int[] as1=bubbleSort(as);
            int[] as2=cp(as);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                break;
            }
        }
    }

    public static int[] cp(int[] as) {
        int[]bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }

    public static void swap(int[] as,int a,int b){
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }

    public static int[] bubbleSort(int[] as) {
        // int[] as={2,1,5,9,5,7,0,8};
        // int[] as={2,1};
        int n=as.length;
        for(int j=n;j>0;j--){ //xcrj j是个数 从2个到n个都会经历冒泡
            for(int i=1;i<j;i++){
                if(as[i-1]>as[i]){
                    swap(as,i,i-1);
                }
            }
        }

        // System.out.println(Arrays.toString(as));
        return as;
    }

    public static int[] getAs(int maxLen,int maxV){
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

}
