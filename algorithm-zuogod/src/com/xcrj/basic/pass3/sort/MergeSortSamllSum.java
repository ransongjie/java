package com.xcrj.basic.pass3.sort;

import java.util.Arrays;

/**
 * 小和问题
 * 右侧比我大的数的个数*我=和
 */
public class MergeSortSamllSum {
    public static int mergeSort(int[]as){
        return process(as,0,as.length-1);
    }

    private static int process(int[]as,int s,int e){
        int sum=0;
        if(s<e){
            int m=(s+e)>>1;
            sum+=process(as,s,m);
            sum+=process(as,m+1,e);
            sum+=merge(as,s,m,e);
        }
        return sum;
    }

    private static int merge(int[]as,int s,int m,int e){
        int sum=0;
        int[]bs=new int[e-s+1];
        int i=s,j=m+1,k=0;
        while(i<=m&&j<=e){
            if(as[i]<=as[j]){
                sum+=(e-j+1)*as[i];//右侧比我大的数的个数*我=和
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
        return sum;
    }

    public static int cp(int[]as) {
        int suma=0;
        int n=as.length;
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j > -1; j--) {
                if(as[i]>=as[j]){
                    suma+=as[j];
                }
            }
        }
        return suma;
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

            int sum1=cp(as1);
            int sum2=mergeSort(as2);
            if(sum1!=sum2){
                System.out.println(Arrays.toString(as));
                System.out.println(sum1);
                System.out.println(sum2);
                throw new RuntimeException();
            }
        }
    }
}
