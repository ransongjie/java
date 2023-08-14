package com.xcrj.basic.pass1.dichotomy;

import java.util.Arrays;

//>=k最左边的数，前提 有序序列。k不在序列中也可以，>k的数就行
public class GeKLeftmost {
    public static void main(String[] args) {
        // int[] as={0, 1, 2, 2, 3, 7, 9, 9};
        // int r=dicho(as,0,as.length-1,6);
        // System.out.println(r);
        // System.out.println(r==-1?-1:as[r]);

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[] as=getSortedAs(maxLen,maxV);
            int k=(int)(Math.random()*maxV);
            int r1=dicho(as, 0, as.length-1, k);
            int r2=cp(as, k);
            if(r1!=r2){
                System.out.println("not good");
                System.out.println(Arrays.toString(as));
                System.out.println("k="+k);
                System.out.println("r1="+r1);
                System.out.println("r2="+r2);
            }
        }
    }
    
    public static int dicho(int[]as,int start,int end,int k) {
        if(start>end){
            return -1;
        }
        int mid=(start+end)/2;
        if(as[mid]>=k){//先满足 >=k
            int t=mid;
            int t1=dicho(as,start,mid-1,k);
            if(t1!=-1&&t1<t){//再满足最左边，更靠近左边则记录
                t=t1;
            }
            return t;
        }
        if(as[mid]<k){
            return dicho(as, mid+1, end, k);
        }
        return -1;
    }

    //>=k
    public static int cp(int[] as,int k) {
        int len=as.length;
        //找>=k的数
        for(int i=0;i<len;i++){
            if(as[i]>=k){
                return i;
            }
        }
        
        return -1;
    }

    public static int[] getSortedAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        Arrays.sort(as);
        return as;
    }
}
