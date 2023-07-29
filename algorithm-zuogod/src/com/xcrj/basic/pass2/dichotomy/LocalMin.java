package com.xcrj.basic.pass2.dichotomy;

import java.util.HashSet;
import java.util.Set;

/**
 * 局部最小值
 * 数组无序
 * 任何两个相邻的数不相等
 */
public class LocalMin {
    public static void main(String[] args) {
        int times=10000;
        int maxL=100;
        int maxV=1000;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxL, maxV);
            int idx=localMin(as);
            Set<Integer> set=cp(as);
            if(idx==-1&&set.size()!=0){
                System.out.println(idx);
                throw new RuntimeException();
            }
            if(idx!=-1&&!set.contains(idx)){
                System.out.println(idx);
                throw new RuntimeException();
            }
        }
    }
    public static int localMin(int[]as) {
        return proccess(as, 0, as.length-1);
    }

    public static int proccess(int[]as,int start,int end) {
        int mid=(start+end)/2;

        if(mid==0&&as[mid]<as[mid+1]){
            return mid;
        }
        if(mid==as.length-1&&as[mid]<as[mid-1]){
            return mid;
        }

        if(as[mid]<as[mid+1]&&as[mid]<as[mid-1]){
            return mid;
        }
        if(as[mid]>as[mid+1]){
            return proccess(as, mid+1, end);//
        }else{
            return proccess(as, start, mid-1);//
        }
    }

    public static int[] getAs(int maxL,int maxV) {
        int len=(int)(Math.random()*(maxL-2))+2;
        int[]as=new int[len];
        as[0]=(int)(Math.random()*maxV);
        for (int i = 1; i < as.length; i++) {
            as[i]=(int)(Math.random()*maxV);
            while(as[i-1]==as[i]){
                as[i]=(int)(Math.random()*maxV);
            }
        }
        return as;
    }

    public static Set<Integer> cp(int[]as) {
        Set<Integer> set=new HashSet<>();
        if(as[0]<as[1]) set.add(0);
        if(as[as.length-1]<as[as.length-2]) set.add(as.length-1);
        for (int i = 1; i < as.length-1; i++) {
            if(as[i]<as[i-1]&&as[i]<as[i+1]){
                set.add(i);
            }
        }

        return set;
    }
}
