package com.xcrj.basic.pass1.sort;

import java.util.Arrays;

//小和, 左侧比我小的数的和 等价于 右侧比我大数的个数
/**
 * 1 3 4 2 5
 * 1 0
 * 3 1
 * 4 4
 * 2 1
 * 5 10
 * sum=16
 * 
 * 1 3 4 2 5
 * 1 4 =1*4=4
 * 3 2 =3*2=6
 * 4 1 =4*1=4
 * 2 1 =2*1=2
 * 5 0 =5*0=0
 * sum=16
 */
public class MergeLSum {
    public static void main(String[] args) {
        // int[] as={2, 9, 1, 3, 6, 2};
        // int[] as={1,3,4,2,5};
        // int sum=mergeSort(as, 0, as.length-1);
        // System.out.println(Arrays.toString(as));
        // System.out.println(sum);

        int times=10000;
        int maxLen=1000;
        int maxV=100;
        for(int i=0;i<times;i++){
            int[]as=getAs(maxLen, maxV);
            int sum1=cp(as);
            MergeLSum.sum=0;
            mergeSort(as, 0, as.length-1);
            int sum2=MergeLSum.sum;
            if(sum1!=sum2){
                System.out.println("not good");
            }
        }
    }

    static int sum=0;
    public static void mergeSort(int[] as,int start,int end) {
        if(start>=end) return;
        int mid=(start+end)/2;
        mergeSort(as, start, mid);
        mergeSort(as, mid+1, end);
        // int sum=0;
        sum+=merge(as,start,mid,end);
    }

    //二路归并 每次 A块和B块对比 块内元素不进行对比(下层已经对比过了)
    public static int merge(int[] as,int start,int mid,int end) {
        int sum=0;
        int[] as2=new int[end-start+1];
        int i1=start;
        int i2=mid+1;
        int k=0;
        while(i1<=mid&&i2<=end){
            if(as[i1]<as[i2]) {//进入merge之前 左侧 和 右侧都有序，左侧和右侧比较即可
                sum+=as[i1]*(end-i2+1);
                as2[k++]=as[i1++];
            }else as2[k++]=as[i2++];
        }
        while(i1<=mid){
            as2[k++]=as[i1++];
        }
        while(i2<=end){
            as2[k++]=as[i2++];
        }
        System.arraycopy(as2, 0, as, start, end-start+1);
        return sum;
    }

    public static int cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        int sum=0;
        int n=bs.length;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<i;j++){
                if(bs[j]<bs[i]){
                    sum+=bs[j];
                }
            }
        }

        return sum;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for(int i=0;i<len;i++){
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
