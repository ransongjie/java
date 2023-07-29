package com.xcrj.company.pass1;

public class A066NextPermutation {

    public static void nextPermutation(int[]as){
        int n=as.length;
        //找到第一个降序的元素
        int firstDownIdx=-1;
        for (int i = n-2; i >=0 ; i++) {
            if(as[i]<as[i+1]){
                firstDownIdx=i;
                break;
            }
        }
        //一直不降序
        if(firstDownIdx==-1){
            reverse(as,0,as.length-1);
        }else{
            //找到首次大于as[firstDownIdx]的元素
            int biggerNearestIdx=-1;
            for (int i = as.length-1; i >firstDownIdx ; i--) {
                if(as[firstDownIdx]<as[i]){
                    biggerNearestIdx=i;
                    break;
                }
            }
            swap(as,firstDownIdx,biggerNearestIdx);
            reverse(as,firstDownIdx+1,as.length-1);
        }
    }

    private static void reverse(int[]as,int l,int r){
        while(l<r){
            swap(as,l++,r--);
        }
    }

    private static void swap(int[]as,int a,int b){
        int tmp=as[a];
        as[a]=as[b];
        as[b]=tmp;
    }
}
