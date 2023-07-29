package com.xcrj.basic.pass3.sort;

import java.util.Arrays;

/**
 * 选择e做轴值，lb内的元素`<e`，rb内的元素`>e`，lb到rb之间的元素等于e
 */
public class QuickSort2 {
    public static void quickSort(int[]as){
        process(as,0,as.length-1);
    }

    public static void process(int[]as,int s,int e){
        if(s<e){
            int[] lbrb=pivot(as,s,e);
            process(as,s,lbrb[0]);
            process(as,lbrb[1],e);
        }
    }

    private static int[] pivot(int[]as,int s,int e){
        int lb=s-1,rb=e;//
        for (int i = s; i < rb; i++) {//
            if(as[i]<as[e]){
                swap(as,++lb,i);
                continue;
            }
            if(as[i]>as[e]){
                swap(as,--rb,i);
                i--;//
                continue;
            }
            if(as[i]==as[e]){
                continue;
            }
        }
        swap(as,rb,e);//
        return new int[]{lb,rb+1};//
    }

    private static void swap(int[]as,int a,int b){
        int temp=as[a];
        as[a]=as[b];
        as[b]=temp;
    }

    private static int[] getAs(int maxLen,int maxV){
        int len=(int)(Math.random()*maxLen+1);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    private static int[] cp(int[] as) {
        Arrays.sort(as);
        return as;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as0=Arrays.copyOf(as, as.length);
            int[] as1=Arrays.copyOf(as, as.length);
            int[] as2=Arrays.copyOf(as, as.length);

            cp(as1);
            quickSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }
}
