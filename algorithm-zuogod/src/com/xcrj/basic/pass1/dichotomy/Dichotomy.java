package com.xcrj.basic.pass1.dichotomy;

import java.util.Arrays;

//经典二分查找
public class Dichotomy {
    public static void main(String[]args) {
        // int[] as={1,2,5,9,5,7,0,8};
        // Arrays.sort(as);
        // System.out.println(Arrays.toString(as));
        // int r=dich(as, 0, as.length-1, 4);
        // System.out.println(r==-1?-1:as[r]);
        // System.out.println(Arrays.binarySearch(as, 4));

        int[] as={1};
        Arrays.sort(as);
        System.out.println(Arrays.toString(as));
        int r=dich(as, 0, as.length, 1);
        System.out.println(r==-1?-1:as[r]);
        System.out.println(Arrays.binarySearch(as, 1));
        
        // int times=10000;
        // int maxLen=1000;
        // int maxV=100;
        // for (int i = 0; i < times; i++) {
        //     int[] as=getSotedAs(maxLen, maxV);
        //     int search=(int)(Math.random()*maxV);
        //     int a=dich(as, 0, as.length-1, search);
        //     int b=cp(as, search);
        //     if(a>=0&&b>=0&&a!=b){
        //         System.out.println("not good");
        //         System.out.println(Arrays.toString(as));
        //         System.out.println(search);
        //         System.out.println(as[a]);
        //         System.out.println(as[b]);
        //     }
        // }
    }

    public static int dich(int[] as,int start,int end,int search) {
        if(start>=end){//找不到
            return -1;
        }
        int mid=(start+end)/2;
        if(as[mid]==search){
            return mid;
        }
        if(as[mid]>search){//左边有没有
            return dich(as, start, mid-1,search);
        }
        if(as[mid]<search){//右边有没有
            return dich(as, mid+1, end,search);
        }
        return -1;//没找到
    }

    public static int cp(int[] as,int search) {
        //not existed, return negtive number maybe not -1.
        return Arrays.binarySearch(as, search);
    }

    public static int[] getSotedAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        Arrays.sort(as);
        return as;
    }
}
