package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
//end做pivot，遍历start到end，左边界leBorder，小于等于的放左边界内
public class QuickSort1 {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // quickSort1(as);
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
            quickSort1(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void quickSort1(int[]as) {
        quickSortIn1(as, 0, as.length-1);
    }

    public static void quickSortIn1(int[]as,int s,int e) {
        if(s<e){
            int pivot=getPivot1(as, s, e);
            quickSortIn1(as,s,pivot-1);
            quickSortIn1(as,pivot+1,e);
        }
    }

    public static int getPivot1(int[]as,int s,int e) {
        int leBorder=s-1;
        for (int i = s; i < e; i++) {
            if(as[i]<=as[e]){
                swap(as, ++leBorder, i);
            }
        }
        swap(as, ++leBorder, e);
        return leBorder;
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
