package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 小和问题
 * 左侧比我小的数的和 = 右边大于我的数的个数*我
 * +法变*法
 */
public class MergeLSum {
    public static void main(String[] args) {
        // int[] as={1,3,4,2,5};//expect 16
        // int sum=mergeSort(as);
        // System.out.println(Arrays.toString(as));
        // System.out.println(sum);

        // int[] as={1,3,4,2,5};//expect 16
        // int sumcp=cp(as);
        // System.out.println(sumcp);

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
                System.out.println("not good");
                System.out.println(Arrays.toString(as));
                System.out.println(sum1);
                System.out.println(sum2);
                throw new RuntimeException();
            }
        }
    }

    public static int mergeSort(int[]as) {
        return proccess(as, 0, as.length-1);
    }

    public static int proccess(int[]as,int s,int e) {
        int sum=0;
        if(s>=e) return sum;
        int m=(s+e)/2;
        sum+=proccess(as, s, m);//left
        sum+=proccess(as, m+1, e);//right
        sum+=merge(as, s, m, e);//merge left and right
        return sum;
    }
    
    public static int merge(int[]as,int s,int m,int e) {
        int sum=0;//
        int[]bs=new int[e-s+1];
        int i=s;
        int j=m+1;
        int k=0;
        while(i<=m&&j<=e){
            if(as[i]<=as[j]){
                sum+=as[i]*(e-j+1);//每个子序列都是有序的，因此可以使用下标
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

        System.arraycopy(bs, 0, as, s, bs.length);

        return sum;
    }

    public static int cp(int[]as) {
        //左侧比我小的数求和
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
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
