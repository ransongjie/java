package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        // int[] as={2, 9, 1, 3, 6, 2};
        // int[] as={2,9,1,3};
        // mergeSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            int[] cs=Arrays.copyOf(as, as.length);
            int[] bs=cp(as);
            mergeSort(as, 0, as.length-1);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                System.out.println(Arrays.toString(cs));
                System.out.println(Arrays.toString(as));
                System.out.println(Arrays.toString(bs));
            }
        }
    }

    public static void mergeSort(int[]as,int start,int end) {
        if(start>=end)return;
        int mid=(start+end)>>1;
        mergeSort(as, start, mid);//左部分排序
        mergeSort(as, mid+1, end);//右部分排序
        merge(as, start, mid, end);//左右部分合起来排序
    }

    public static void merge(int[] as,int start,int mid,int end) {
        int[] as2=new int[end-start+1];
        int i1=start;
        int i2=mid+1;
        int k=0;
        while(i1<=mid&&i2<=end){
            if(as[i1]<=as[i2]){
                as2[k++]=as[i1++];
            }else{
                as2[k++]=as[i2++];
            }
        }

        while(i1<=mid){
            as2[k++]=as[i1++];
        }

        while(i2<=end){
            as2[k++]=as[i2++];
        }

        System.arraycopy(as2, 0, as, start, end-start+1);
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

    public static int[] cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }
}
