package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] as={2,1,5,9,5,7,0,8};
        heapSort(as);
        System.out.println(Arrays.toString(as));

        // int[] as={9,6,5,3,4,5,0};
        // int[] as={1,6,5,3,4,5,0};
        // int[] as={1,2};
        // for (int i = 0; i < as.length; i++) {
        //     heapInsert(as[i]);
        // }
        // for (int i = 0; i < heapSize; i++) {
        //     System.out.print(rs[i]+", ");
        // }

        // int[] as={1,6,5,3,4,5,0};
        // for (int i = 0; i < as.length; i++) {//i相当于 heapSize
        //     heapInsert2(as, i);
        // }
        // System.out.println(Arrays.toString(as));

        // for (int i = as.length-1; i >=0; i--) {
        //     swap(as, 0, i);
        //     heapfy(as, i);
        // }
        // System.out.println(Arrays.toString(as));

        // int times=10000;
        // int maxLen=10000;
        // int maxV=100;
        // for (int i = 0; i < times; i++) {
        //     int[] as=getAs(maxLen, maxV);
        //     int[] cs=Arrays.copyOf(as, as.length);
        //     int[] bs=cp(as);
        //     heapSort(as);
        //     if(!Arrays.equals(as, bs)){
        //         System.out.println("not good");
        //         System.out.println(Arrays.toString(cs));
        //         System.out.println(Arrays.toString(bs));
        //         System.out.println(Arrays.toString(as));
        //     }   
        // }
    }

    static int[] rs=new int[100];
    static int heapSize=0;
    public static void heapInsert(int a) {
        if(heapSize==0){
            rs[0]=a;
            heapSize++;
            return;
        }
        rs[heapSize]=a;
        int i=heapSize;
        while(i>0){
            int p=0;
            if(i%2==0){
                p=(i-2)/2;
            }
            if(i%2==1){
                p=(i-1)/2;
            }
            if(rs[i]>rs[p]){
                swap(rs, i, p);
            }
            i=p;
        }
        heapSize++;
        return;
    }

    public static void heapInsert2(int[] as,int idx) {
        while(as[idx]>as[(idx-1)/2]){
            swap(as, idx, (idx-1)/2);
            idx=(idx-1)/2;
        }
    }

    //不取end
    private static void heapfy(int[] as, int heapSize) {
        int i=0;
        int l=1;
        int r=2;
        while(l<heapSize){
            int g=0;
            if(r>=heapSize||as[l]>=as[r]) g=l;//需要考虑只有2个结点的情况
            else g=r;
            if(as[g]>as[i]) swap(as, g, i);
            else break;//
            i=g;
            l=2*i+1;
            r=2*i+2;
        }
    }

    

    private static void heapSort(int[] as) {
        //从0~as.length把大的放到前面去
        for (int i = 0; i < as.length; i++) {//i相当于 heapSize
            heapInsert2(as, i);
        }

        for (int i = as.length-1; i >=0; i--) {
            swap(as, 0, i);//大根堆，交换后正序
            heapfy(as, i);
        }
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

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }
}
