package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class HeapSort2 {
    public static void main(String[] args) {
        // int[] as={5, 4, 4, 3, 7};
        // int[] as={1,6};
        // int[] as={1};
        // heapSort2(as);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            int[] cs=Arrays.copyOf(as, as.length);
            int[] bs=cp(as);
            heapSort2(as);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                System.out.println(Arrays.toString(cs));
                System.out.println(Arrays.toString(bs));
                System.out.println(Arrays.toString(as));
            }   
        }
    }

    //不取end
    private static void heapfy2(int[] as, int start,int end) {
        int i=start;
        int l=start*2+1;
        int r=start*2+2;
        while(l<end){
            int g=0;
            if(r>=end||as[l]>=as[r]) g=l;//需要考虑只有2个结点的情况
            else g=r;
            if(as[g]>as[i]) swap(as, g, i);
            else break;//
            i=g;
            l=2*i+1;
            r=2*i+2;
        }
    }

    private static void heapSort2(int[] as) {
        int n=as.length-1;
        for (int i = (n-1)/2; i>=0; i--) {
            heapfy2(as, i, as.length);
        }
        for (int i = n; i >=0; i--) {
            swap(as, 0, i);
            heapfy2(as,0, i);
        }
    }

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }

    public static int[] getAs(int maxLen, int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for (int i = 0; i < as.length; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static int[] cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }
}
