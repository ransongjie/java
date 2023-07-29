package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class QuickSort2 {
    public static void main(String[] args) {
        // int[] as={8,2,1,5};
        // quickSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            int[] cs=Arrays.copyOf(as, as.length);
            int[] bs=cp(as);
            quickSort(as, 0, as.length-1);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                System.out.println(Arrays.toString(cs));
                System.out.println(Arrays.toString(as));
                break;
            }
        }
    }

    public static void quickSort(int[]as,int start,int end) {
        if(start<end){
            int[] lr=partition(as, start, end);
            quickSort(as, start, lr[0]);
            quickSort(as, lr[1], end);
        }
    }

    public static int[] partition(int[]as,int start,int end) {
        int ll=start-1;
        int rl=end;//i~end-1,end
        int i=start;
        while(i<rl){//xcrj 
            if(as[i]<as[end]){
                swap(as, ll+1, i);
                ll++;
                i++;
                continue;//
            }
            if(as[i]>as[end]){
                swap(as, rl-1, i);
                rl--;
                continue;//
            }
            if(as[i]==as[end]){
                i++;
                continue;//
            }
        }
        swap(as, rl, end);//2 1 2 3 3【5 6 4 3。3和5交换
        rl++;
        return new int[]{ll,rl};
    }

    public static void swap(int[] as,int a,int b){
        // as[a]=as[a]^as[b];
        // as[b]=as[a]^as[b];
        // as[a]=as[a]^as[b];
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }

    public static int[] cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
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
