package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class QuickSort1 {
    public static void main(String[] args) {
        // int[] as={1,2,5,9,5,7,0,8};
        // quickSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            quickSort(as, 0, as.length-1);
            int[] bs=cp(as);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                break;
            }
        }
    }

    public static void quickSort(int[] as, int start, int end) {
        if(start<end){
            int pivot=partition(as,start,end);
            quickSort(as, start, pivot-1);
            quickSort(as, pivot+1, end);
        }
    }
    //start到end遍历, <=end的放到end左边, >end放到end右边
    public static int partition(int[] as,int start,int end) {
        int llimit=start-1;
        int i=start;
        for(;i<end;i++){
            if(as[i]<=as[end]){
                swap(as, i, llimit+1);
                llimit++;
            }
        }
        swap(as, llimit+1, end);
        llimit++;
        return llimit;
    }

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static int[] cp(int[] as){
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }
}
