package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

//
public class QuickSort3 {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // quickSort3(as);
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
            quickSort3(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void quickSort3(int[]as) {
        quickSortIn3(as, 0, as.length-1);
    }

    public static void quickSortIn3(int[]as,int s,int e) {
        if(s<e){
            int[] pivots=getPivots(as, s, e);
            quickSortIn3(as,s,pivots[0]);
            quickSortIn3(as,pivots[1],e);
        }
    }

    public static int[] getPivots(int[]as,int s,int e) {
        int rnd=(int)(s+Math.random()*(e-s+1));//
        swap(as, e, rnd);
        int lborder=s-1;
        int rborder=e;
        for (int i = s; i < rborder; i++) {
            if(as[i]<as[e]){
                swap(as, ++lborder, i);
                continue;
            }
            if(as[i]>as[e]){
                swap(as, --rborder, i);
                i--;
                continue;
            }
            if(as[i]==as[e]){
                continue;
            }
        }
        swap(as, rborder, e);
        return new int[]{lborder,rborder+1};
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
