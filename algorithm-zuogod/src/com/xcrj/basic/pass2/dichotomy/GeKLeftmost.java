package com.xcrj.basic.pass2.dichotomy;

import java.util.Arrays;

/**
 * >=k的最左边的数
 * 
 */
public class GeKLeftmost {
    public static void main(String[] args) {
        int times=10000;
        int maxV=100;
        int maxL=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxL, maxV);
            Arrays.sort(as);
            int k=(int)(Math.random()*maxV);
            int mostLeft=cp(as, k);
            int mostLeft2=geKLeftmost(as,k);
            if(mostLeft!=mostLeft2){
                System.out.println(Arrays.toString(as));
                System.out.println(mostLeft);
                System.out.println(mostLeft2);
                throw new RuntimeException();
            }
        }
    }

    public static int geKLeftmost(int[]as,int k) {
        return proccess(as, k, 0, as.length-1);
    }

    private static int proccess(int[]as,int k,int start,int end) {
        if(start>end) return -1;
        int mid=(start+end)/2;
        if(as[mid]>=k){// >=k，找到一个满足条件的数后，继续往左看有没有更左的满足条件的数
            int t=mid;
            int t1=proccess(as, k, start, mid-1);
            if(t1!=-1&&t1<t){
                t=t1;
            }
            return t;
        }
        if(as[mid]<k){
            return proccess(as, k, mid+1, end);
        }
        return -1;
    }

    public static int[] getAs(int maxL,int maxV) {
        int len=(int)(Math.random()*(maxL-1)+1);
        int[]as=new int[len];
        for (int i = 0; i < as.length; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static int cp(int[]as ,int k) {
        for (int i = 0; i < as.length; i++) {
            if(as[i]>=k) return i;
        }
        return -1;
    }
}
