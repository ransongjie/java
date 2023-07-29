package com.xcrj.basic.pass2.dichotomy;

import java.util.Arrays;
/**
 * 需要有序
 * 经典二分查找
 */
public class Dichotomy {
    public static void main(String[] args) {
        int times=10000;
        int maxL=100;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxL, maxV);
            Arrays.sort(as);
            int search=(int)(Math.random()*maxV);
            int idx=dichotomy(as,search);
            int idx2=Arrays.binarySearch(as, search);
            if(idx==-1){
                if(idx2>-1){
                    System.out.println(Arrays.toString(as));
                    System.out.println(idx);
                    System.out.println(idx2);
                    System.out.println(search);
                    throw new RuntimeException();
                }
                continue;
            }
            
            if(search!=as[idx]||as[idx]!=as[idx2]){
                System.out.println(Arrays.toString(as));
                System.out.println(search);
                System.out.println(as[idx]);
                throw new RuntimeException();
            }
        }
    }

    public static int dichotomy(int[]as,int search) {
        return proccess(as, search, 0, as.length-1);//
    }

    private static int proccess(int[]as,int search,int start,int end) {
        if(start>end) return -1;

        int mid=(start+end)/2;
        if(as[mid]==search) return mid;
        if(as[mid]>search) return proccess(as, search, start, mid-1);//return
        if(as[mid]<search) return proccess(as, search, mid+1, end);//
        return -1;
    }

    public static int[] getAs(int maxL,int maxV) {
        int len=(int)(Math.random()*(maxL-1)+1);
        int[]as=new int[len];
        for (int i = 0; i < as.length; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
