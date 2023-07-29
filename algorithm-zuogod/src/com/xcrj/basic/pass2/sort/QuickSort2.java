package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
//改进 小于e，等于e，大于e。左边界，右边界
public class QuickSort2 {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // quickSort2(as);
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
            quickSort2(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void quickSort2(int[]as) {
        quickSortIn2(as, 0, as.length-1);
    }

    public static void quickSortIn2(int[]as,int s,int e) {
        if(s<e){
            int[] pivots=getLRBorder(as, s, e);
            quickSortIn2(as, s, pivots[0]);//
            quickSortIn2(as, pivots[1], e);//
        }
    }

    public static int[] getLRBorder(int[]as,int s,int e) {
        //已经交换好的位置
        int lborder=s-1;
        int rborder=e;
        for (int i = s; i < rborder; i++) {//
            if(as[i]<as[e]){
                swap(as, ++lborder, i);
                continue;
            }

            if(as[i]>as[e]){
                swap(as, --rborder, i);
                i--;//
                continue;
            }

            if(as[i]==as[e]){
                continue;
            }
        }

        swap(as, rborder, e);//2 1 0 3 5 7 9 7 5, 最后的5和第1个7交换
        return new int[]{lborder,rborder+1};//
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
