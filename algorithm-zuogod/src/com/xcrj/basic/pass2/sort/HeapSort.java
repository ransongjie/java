package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // heapSort(as);
        // System.out.println(Arrays.toString(as));

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as0=Arrays.copyOf(as, as.length);
            int[] as1=Arrays.copyOf(as, as.length);
            int[] as2=Arrays.copyOf(as, as.length);

            cp(as1);
            heapSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }
    // 大根堆
    public static void heapSort(int[]as) {
        //初始建堆
        for (int i = 0; i < as.length; i++) {
            heapInsert(as, i);
        }

        //重建堆
        int heapSize=as.length-1;
        for (int i = 0; i < as.length; i++) {//i < as.length
            swap(as, 0, heapSize);
            heapSize--;
            heapfy(as, heapSize);// 只有堆顶元素无序
        }
    }

    public static void heapInsert(int[]as,int i) {//
        while(as[i]>as[(i-1)/2]){
            swap(as, i, (i-1)/2);
            i=(i-1)/2;
        }
    }

    public static void heapfy(int[]as,int heapSize) {
        int i=0;
        int j=1;
        while(j<=heapSize){
            int g=0;
            if(j+1>heapSize) g=j;//
            else{
                if(as[j]>=as[j+1]) g=j;
                else g=j+1;
            }
            if(as[i]<as[g]) swap(as, i, g);
            else break;//
            i=g;
            j=2*i+1;
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
