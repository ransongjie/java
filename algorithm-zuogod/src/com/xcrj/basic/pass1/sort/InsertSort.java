package com.xcrj.basic.pass1.sort;
import java.util.Arrays;
//小范围交换
public class InsertSort {
    public static void main(String[] args) {
        int times=10000;
        int maxLen=1000;
        int maxV=1000;
        for(int i=0;i<times;i++){
            int[] as=getAs(maxLen,maxV);
            int[] as1=insertSort(as);
            int[] as2=cp(as);
            if(!Arrays.equals(as1, as2)){
                System.out.println("not good");
                break;
            }
        }
    }

    public static int[] insertSort(int[] as) {
        // int[] as={1,2,5,9,5,7,0,8};
        // int[] as={3,2,1,0};
        int n=as.length;
        for(int i=1;i<n;i++){
            int tmp=as[i];
            int j=i-1;
            for(;j>=0&&tmp<as[j];j--){//后移
                as[j+1]=as[j];
            }
            as[j+1]=tmp;//tmp最终插入的位置
        }
        // System.out.println(Arrays.toString(as));

        return as;
    }

    public static int[] cp(int[] as) {
        int[] bs=Arrays.copyOf(as, as.length);
        Arrays.sort(bs);
        return bs;
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