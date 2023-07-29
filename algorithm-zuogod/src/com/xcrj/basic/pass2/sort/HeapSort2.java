package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 堆排序只使用heapfy
 */
public class HeapSort2 {
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

    public static void heapSort(int[]as) {
        //初始逆序建立堆
        int n=as.length;
        int heapSize=n-1;
        for (int i = n/2; i >-1; i--) {//
            heapfy(as, i, heapSize);
        }
        //交换重建堆
        for (int i = 0; i < n; i++) {
            swap(as, 0, heapSize);
            heapSize--;
            heapfy(as, 0, heapSize);
        }
    }
    public static void heapfy(int[]as,int p,int heapSize) {
        int i=p;
        int j=i*2+1;
        while(j<=heapSize){
            int g=j;//
            if(j+1<=heapSize){//
                if(as[j]>=as[j+1])g=j;
                else g=j+1;
            }
            if(as[i]<as[g]) swap(as, i, g);
            else break;
            
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
