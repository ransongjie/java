package com.xcrj.basic.pass1.dichotomy;

import java.util.HashSet;
import java.util.Set;

//局部最小值，数组中元素值不一定有序,任何两个相邻的数不相等,下降趋势有序
public class LocalMin {
    public static void main(String[]args) {
        // int[] as={3,1,2,1,2,3};
        // int[]as={3,1,1,2};
        // int idx=dicho(as, 0, as.length-1);
        // System.out.println(idx);
        // int idx2=cp(as);
        // System.out.println(idx2);

        // int times=10000;
        // int maxLen=100;
        // int maxV=10;
        // for (int i = 0; i < times; i++) {
        //     int[] as=getAs(maxLen, maxV);
        //     if(as.length==0||as.length==1) continue;
        //     int r=dicho(as,0,as.length-1);
        //     Set<Integer> set=cp(as);
        //     if(!set.contains(r)){
        //         System.out.println("not good");
        //         System.out.println(Arrays.toString(as));
        //         System.out.println(r);
        //         System.out.println(set.toString());
        //     }
        // }

        // int[] as={3,1,2};
        // int idx=findLocalMin(as);
        // System.out.println(idx);

        // int times=10000;
        // int maxLen=1000;
        // int maxV=100;
        // for (int i = 0; i < times; i++) {
        //     int[] as=getAs(maxLen, maxV);
        //     if(as.length==0||as.length==1) continue;
        //     int r=findLocalMin(as);
        //     Set<Integer> set=cp(as);
        //     if(!set.contains(r)){
        //         System.out.println("not good");
        //         System.out.println(Arrays.toString(as));
        //         System.out.println(r);
        //         System.out.println(set.toString());
        //     }
        // }
    }

    public static int dicho(int[]as,int start,int end) {
        if(start>=end){
            return -1;
        }
        if(as[start]<as[start+1]){
            return start;
        }
        if(as[end]<as[end-1]){
            return end;
        }
        //mid-1,mid,mid+1 共有6中情况 情况考虑多了 题目条件任意两个元素值不相等
        int mid=(start+end)/2;
        if(as[mid]<as[mid+1]&&as[mid]<as[mid-1]){
            return mid;
        }
        if(mid-1>=start&&as[mid]>as[mid-1]){
            return dicho(as,start,mid-1);
        }
        if(mid+1<=end&&as[mid]<as[mid+1]){
            return dicho(as,start,mid-1);
        }
        if(mid+1<=end&&as[mid]>as[mid+1]){
            return dicho(as,mid+1,end);
        }
        if(mid-1>=start&&as[mid]<as[mid-1]){
            return dicho(as,mid+1,end);
        }
        if(mid-1>=start&&mid+1<=end&&as[mid]==as[mid-1]&&as[mid]==as[mid+1]){
            int t= dicho(as,start,mid-1);
            if(t!=-1){
                return t;
            }
            return dicho(as,mid+1,end);
        }
        return -1;
    }

    public static int findLocalMin(int[]as) {
        //特殊情况
        if(as==null||as.length<2){
            return -1;
        }
        if(as[0]<as[1]){
            return 0;
        }
        int l=as.length-1;
        if(as[l]<as[l-1]){
            return l;
        }

        return dicho2(as, 0, l);//一般情况
    }

    public static int dicho2(int[]as,int start,int end) {
        // if(start>end){
        //     return -1;
        // }

        int mid=(start+end)/2;
        if(as[mid]<as[mid+1]&&as[mid]<as[mid-1]){
            return mid;
        }

        //任意两个元素值不等 所以只有这两种情况 1个数大1个数小
        if(as[mid]>as[mid+1]){
            return dicho2(as, mid+1, end);
        }else{
            return dicho2(as, start, mid-1);
        }
    }

    //数组中元素值不一定有序,任何两个相邻的数不相等
    //as[]长度至少为1，且相邻两个数不相同
    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        while(len==0){
            len=(int)(Math.random()*maxLen);
        }
        int[] as=new int[len];
        int v=(int)(Math.random()*maxV);
        as[0]=v;
        for(int i=1;i<len;i++){
            while(as[i-1]==v){
                v=(int)(Math.random()*maxV);
            } 
            as[i]=v;
        }
        return as;
    }

    public static Set<Integer> cp(int[]as) {
        Set<Integer> set=new HashSet<>();
        if(as.length==0){
            set.add(-1);
            return set;
        }
        if(as.length==1){
            set.add(-1);
            return set;
        }
        if(as[0]<as[1]){
            set.add(0);
            return set;
        }
        int l=as.length-1;
        if(as[l]<as[l-1]){
            set.add(l);
            return set;
        }
        
        for(int i=1;i<l;i++){
            if(as[i]<as[i-1]&&as[i]<as[i+1]){
                set.add(i);
            }
        }

        return set;
    }
}
