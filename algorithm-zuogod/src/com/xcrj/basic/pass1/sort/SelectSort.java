package com.xcrj.basic.pass1.sort;

import java.util.Arrays;
//一趟最后交换
public class SelectSort {
    public static void main(String[] args) {
        int times=10000;
        int maxLen=1000;
        int maxV=1000;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen, maxV);
            int[] as1=selectSort(as);
            int[] as2=cp(as);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                break;
            }
        }

        // System.out.println(Arrays.toString(as));
    }

    public static int[] selectSort(int[] as){
        // int[] as={1,2,5,9,5,7,0,8};
        // int[] as={1,2,5};
        int n =as.length;
        for(int i=0;i<n;i++){
            int idx=i;
            for(int j=i+1;j<n;j++){
                if(as[j]<as[idx]){
                    idx=j;
                }
            }
            swap(as,idx,i);
        }
        //
        // System.out.println(Arrays.toString(as));
        return as;
    }

    //对数器
    public static int[] cp(int[] as){
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
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
