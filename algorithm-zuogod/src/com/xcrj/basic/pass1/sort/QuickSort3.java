package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

public class QuickSort3 {
    public static void main(String[] args) {
        // int[] as={2, 9, 1, 3, 6, 2};
        // quickSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen, maxV);
            int[]cs=Arrays.copyOf(as, as.length);
            quickSort(as, 0, as.length-1);
            int[]bs=cp(as);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                System.out.println("排序前："+Arrays.toString(cs));
                System.out.println("排序后："+Arrays.toString(as));
            }
        }
    }

    public static void quickSort(int[] as,int start,int end) {
        if(start<end){
            int[] lr=partition(as, start,end);
            quickSort(as, start, lr[0]);
            quickSort(as, lr[1], end);
        }
    }

    public static int[] partition(int[] as,int start,int end) {
        int ll=start-1;
        int rl=end;
        int i=start;
        //随机值
        int e=(int)(start+Math.random()*(end-start)+1);//
        swap(as, e, end);
        while(i<rl){
            if(as[i]<as[end]){
                swap(as,i,ll+1);
                ll++;
                i++;
                continue;
            }
            if(as[i]>as[end]){
                swap(as, i, rl-1);
                rl--;
            }
            if(as[i]==as[end]){
                i++;
            }
        }
        swap(as, rl, end);
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

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static int[] cp(int[] as) {
        int[]bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
    }
}
