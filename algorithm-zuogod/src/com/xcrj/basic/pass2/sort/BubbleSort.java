package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
// 每次都可能交换
public class BubbleSort {
    public static void main(String[] args) {
        // int[] as={2,1,5,9,5,7,0,8};
        // bubbleSort(as);
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
            bubbleSort(as2);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }

    public static void bubbleSort(int[]as) {
        int n=as.length;
        for (int i = n; i >0; i--) {//边界
            for (int j = 1; j < i; j++) {//1
                if(as[j-1]>as[j]){
                    swap(as,j-1,j);
                }
            }
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
