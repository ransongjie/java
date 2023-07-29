package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class QuickSort {
    
    public static void main(String[] args) {
        int[] as={1,2,5,9,5,7,0,8};
        quickSort(as,0,as.length-1);
        System.out.println(Arrays.toString(as));
    }

    public static void quickSort(int[] as,int start,int end) {
        if(start<end){
            int pivot=partition(as,start,end);
            quickSort(as,start,pivot-1);//
            quickSort(as,pivot+1,end);//
        }
    }

    public static int partition(int[] as,int start,int end) {
        //实质将<start元素放到start左边，>start元素放到start右边
        int i=start;
        int j=end;
        while(i<j){
            //小于的放左边
            while(i<j&&as[i]<as[j]) j--;
            if(i<j){
                swap(as,i,j);
                i++;
            }
            //大于的放右边
            while(i<j&&as[i]<as[j]) i++;
            if(i<j){
                swap(as,i,j);
                j--;
            }
        }
        return i;
    }

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }
}
