package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 归并排序 
 * 控制子序列长度，再归并
 */
public class MergeSort {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // mergeSort(as);
        // System.out.println(Arrays.toString(as));

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as0=Arrays.copyOf(as, as.length);
            int[] as1=Arrays.copyOf(as, as.length);
            int[] as2=Arrays.copyOf(as, as.length);

            cp(as1);
            mergeSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void mergeSort(int[]as) {
        mergeSortIn(as, 0, as.length-1);
    }

    public static void mergeSortIn(int[]as,int s,int e) {
        if(s>=e) return;//
        int m=(s+e)/2;
        mergeSortIn(as, s, m);
        mergeSortIn(as, m+1, e);
        merge(as, s, m, e);
    }

    public static void merge(int[]as,int s,int m,int e) {
        int[]bs=new int[e-s+1];
        int i=s;
        int j=m+1;
        int k=0;
        while(i<=m&&j<=e){//
            if(as[i]<=as[j]){
                bs[k++]=as[i++];
            }else{
                bs[k++]=as[j++];
            }
        }

        while(i<=m){//
            bs[k++]=as[i++];
        }
        while(j<=e){//
            bs[k++]=as[j++];
        }

        System.arraycopy(bs, 0, as, s, bs.length);
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

    public static int[] cp(int[] as) {
        Arrays.sort(as);
        return as;
    }
}
