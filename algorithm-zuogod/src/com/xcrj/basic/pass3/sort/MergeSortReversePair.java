package com.xcrj.basic.pass3.sort;

import java.util.Arrays;

/**
 * 逆序对总数
 * 右侧比我小的数的个数
 */
public class MergeSortReversePair {
    public static int mergeSort(int[]as){
        return process(as,0,as.length-1);
    }

    private static int process(int[]as,int s,int e){
        int num=0;
        if(s<e){
            int m=(s+e)>>1;
            num+=process(as,s,m);
            num+=process(as,m+1,e);
            num+=merge(as,s,m,e);
        }
        return num;
    }

    private static int merge(int[]as,int s,int m,int e){
        int num=0;
        int[]bs=new int[e-s+1];
        int i=s,j=m+1,k=0;
        while(i<=m&&j<=e){
            if(as[i]>as[j]){//逆序
                num+=e-j+1;//右侧子序列已经逆序
                bs[k++]=as[i++];
            }else{
                bs[k++]=as[j++];
            }
        }

        while(i<=m){
            bs[k++]=as[i++];
        }
        while(j<=e){
            bs[k++]=as[j++];
        }

        System.arraycopy(bs,0,as,s,bs.length);
        return num;
    }

    public static int cp(int[]as) {
        int num=0;
        int n=as.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(as[i]>as[j]){
                    num++;
                }
            }
        }

        return num;
    }


    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen+1);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxLen=100;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen, maxV);
            int[]as1=Arrays.copyOf(as, as.length);
            int[]as2=Arrays.copyOf(as, as.length);

            int num1=cp(as1);
            int num2=mergeSort(as2);
            if(num1!=num2){
                System.out.println(Arrays.toString(as));
                System.out.println(num1);
                System.out.println(num2);
                throw new RuntimeException();
            }
        }
    }
}
