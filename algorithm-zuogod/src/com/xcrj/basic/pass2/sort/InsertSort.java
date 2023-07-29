package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
//插入到合适的位置
public class InsertSort {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // insertSort(as);
        // System.out.println(Arrays.toString(as));

        int times=10000;
        int maxLen=100;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as0=Arrays.copyOf(as, as.length);
            int[] as1=Arrays.copyOf(as, as.length);
            int[] as2=Arrays.copyOf(as, as.length);

            cp(as1);
            insertSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void insertSort2(int[]as) {
        int n=as.length;
        for (int i = 1; i < n; i++) {//第i张牌和前面i-1张牌对比
            int tmp=as[i];
            for (int j = i-1;j >-1; j--) {//我比你小我两交换 不停重复这个过程
                if(as[j]>tmp){
                    swap(as, j, j+1);
                }
            }
        }
    }

    public static void insertSort(int[]as) {
        int n=as.length;
        for (int i = 1; i < n; i++) {//第i张牌和前面i-1张牌对比
            int tmp=as[i];
            int j = i-1;
            for (;j >-1; j--) {//后移
                if(as[j]>tmp){
                    as[j+1]=as[j];
                }else break;//!!! 有一张牌比我小就停止
            }
            as[j+1]=tmp;//
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
