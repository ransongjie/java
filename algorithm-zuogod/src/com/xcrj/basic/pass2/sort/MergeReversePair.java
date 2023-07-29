package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 逆序对
 * 右边有多少数比当前数小，求数量
 */
public class MergeReversePair {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // int num1=cp(as);
        // System.out.println(num1);

        // int[] as={2,1,5,9,5,7,0,8};
        // int num2=mergeSort(as);
        // System.out.println(num2);


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
                System.out.println("not good");
                System.out.println(Arrays.toString(as));
                System.out.println(num1);
                System.out.println(num2);
                throw new RuntimeException();
            }
        }
    }

    public static int mergeSort(int[]as) {
        return proccess(as, 0, as.length-1);
    }

    public static int proccess(int[]as,int s,int e) {
        int num=0;
        if(s>=e)return num;
        int m=(s+e)/2;
        num+=proccess(as, s, m);
        num+=proccess(as, m+1, e);
        num+=merge(as, s, m, e);
        return num;
    }

    public static int merge(int[]as,int s,int m,int e) {
        int num=0;//
        int[]bs=new int[e-s+1];
        int i=s;
        int j=m+1;
        int k=0;
        while(i<=m&&j<=e){//倒序
            if(as[i]>as[j]){
                num+=(e-j+1);//
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
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
