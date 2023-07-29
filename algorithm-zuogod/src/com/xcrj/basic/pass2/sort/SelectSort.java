package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
//索引记录最小值 一趟最后交换
public class SelectSort {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // insertSort(as);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=100;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as0=Arrays.copyOf(as, as.length);
            int[] as1=Arrays.copyOf(as, as.length);
            int[] as2=Arrays.copyOf(as, as.length);

            cp(as1);
            selectSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void selectSort(int[]as) {
        int n=as.length;
        for (int i = 0; i < n-1; i++) {//这一趟的最小值放到什么位置
            int minIdx=i;
            for (int j = i+1; j < n; j++) {
                if(as[j]<as[minIdx]){
                    minIdx=j;
                }
            }
            if(minIdx!=i){
                swap(as, minIdx, i);
            }
        }
    }

    public static void swap(int[]as,int i,int j) {
        int tmp=as[i];
        as[i]=as[j];
        as[j]=tmp;
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
