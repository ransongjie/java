package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

/**
 * 逆序对个数，右侧比我小的数的个数
 */
public class MergeReversePair {
    public static void main(String[] args) {
        // int[] as={5,2,4,3,1};
        // int[] as={5,2,4,3,1};
        // mergeSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));
        // System.out.println(MergeReversePair.num);

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            int num1=cp(as);
            MergeReversePair.num=0;
            mergeSort(as, 0, as.length-1);
            int num2=MergeReversePair.num;
            if(num1!=num2){
                System.out.println("not good");
                System.out.println(num1);
                System.out.println(num2);
            }
        }
    }

    static int num=0;
    public static void mergeSort(int[]as,int start,int end) {
        if(start>=end) return;
        int mid=(start+end)>>1;
        mergeSort(as, start, mid);
        mergeSort(as, mid+1, end);
        num+=merge(as, start, mid, end);
    }

    public static int merge(int[]as,int start,int mid,int end) {
        int num=0;
        int i1=start;
        int i2=mid+1;
        int[] as2=new int[end-start+1];
        int k=0;
        while(i1<=mid&&i2<=end){//降序 进入merge方法的两块 块内已经降序
            if(as[i1]>as[i2]) {
                num+=(end-i2+1);
                as2[k++]=as[i1++];
            }
            else as2[k++]=as[i2++];
        }
        while(i1<=mid){
            as2[k++]=as[i1++];
        }
        while(i2<=end){
            as2[k++]=as[i2++];
        }
        System.arraycopy(as2, 0, as, start, as2.length);
        return num;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }

    public static int cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        int n=bs.length;
        int num=0;
        for (int i = 0; i < n; i++) {
            for(int j=i+1;j<n;j++){
                if(bs[j]<bs[i]) num++;
            }
        }
        return num;
    }
}
