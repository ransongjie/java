package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

//基数排序
public class RadixSort {
    public static void main(String[] args) {
        // int[] as={162,13,21,11};
        // int[] as={};
        // radixSort(as);
        // System.out.println(Arrays.toString(as));

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getPositiveAs(maxLen, maxV);
            int[]cs=Arrays.copyOf(as, as.length);
            int[]bs=cp(as);
            radixSort(as);
            if(!Arrays.equals(as, bs)){
                System.out.println("not good");
                System.out.println(Arrays.toString(cs));
                System.out.println(Arrays.toString(bs));
                System.out.println(Arrays.toString(as));
            }
        }
    }

    public static void radixSort(int[]as) {
        int maxK=0;
        for (int i = 0; i < as.length; i++) {
            int a=as[i];
            int k=0;
            while(a/10>0){
                k++;
                a/=10;
            }
            if(maxK<k){
                maxK=k;
            }
        }
        
        int[] help=new int[as.length];
        for(int i=0;i<=maxK;i++){
            //cnts
            int[] cnts=new int[10];
            for (int j = 0; j < as.length; j++) {
                int a=eachV(as[j],i);
                cnts[a]++;
            }
            
            //累加
            for (int j = 1; j < cnts.length; j++) {
                cnts[j]+=cnts[j-1];
            }
            
            //help
            for (int j = as.length-1; j >=0 ; j--) {
                int a=eachV(as[j],i);
                int idx=cnts[a]-1;
                help[idx]=as[j];
                cnts[a]--;
            }
            System.arraycopy(help, 0, as, 0, as.length);
        }
    }

    //求第i位的值, i=0求最低位
    public static int eachV(int a,int i) {
        int c=a;
        int k=0;
        while(c/10>0){
             k++;
            c/=10;
        }
        
        if(i>k){
            return 0;
        }

        for (int j = 0; j < i; j++) {
            a/=10;
        }
        a%=10;
        return a;
    }

    public static int[] getPositiveAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            while(v<0){
                v=(int)(Math.random()*maxV);
            }
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
