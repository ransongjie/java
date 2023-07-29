package com.xcrj.basic.pass1.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//中位数，数据流随时能够取得中位数
public class HeapMedian {
    static PriorityQueue<Integer> bigQue=new PriorityQueue<>((o1,o2)->o2-o1);
    static PriorityQueue<Integer> smlQue=new PriorityQueue<>((o1,o2)->o1-o2);

    public static void main(String[] args) {
        // int[] as={3,1,1};
        // for (int i = 0; i < as.length; i++) {
        //     float median=heapMedian(as[i]);
        //     System.out.println(median);
        // }

        int times=3000;
        int maxLen=100;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen, maxV);
            for (int j = 0; j < as.length; j++) {
                float median1=cp(as[j]);
                float median2=heapMedian(as[j]);
                if(median1!=median2){
                    System.out.println("not good");
                    System.out.println(Arrays.toString(as));
                    System.out.println(j+" "+as[j]);
                    System.out.println(median1);
                    System.out.println(median2);
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * left median right
     * left小的数字在大根堆，右侧较大的数在小根堆
     * @param a 数据流中的1个数
     * @return 中位数
     */
    public static float heapMedian(int a) {
        //1个数直接入大根堆
        if(bigQue.isEmpty()) {
            bigQue.offer(a);
            return bigQue.peek();
        }else{//后续的数
            //先加入
            if(a>bigQue.peek()){
                smlQue.offer(a);
            }else{
                bigQue.offer(a);
            }
            //再判断size差>=2
            if(bigQue.size()-smlQue.size()>=2){
                smlQue.offer(bigQue.poll());
            }
            if(smlQue.size()-bigQue.size()>=2){
                bigQue.offer(smlQue.poll());
            }
        }
        //
        if(bigQue.size()==smlQue.size()){
            return (float)(bigQue.peek()+smlQue.peek())/2;
        }
        if(bigQue.size()>smlQue.size()){
            return bigQue.peek();
        }
        if(smlQue.size()>bigQue.size()){
            return smlQue.peek();
        }
        return 0.0f;
    }

    static List<Integer> ls=new ArrayList<>();
    public static float cp(int a) {
        ls.add(a);
        Collections.sort(ls);
        int size=ls.size();
        int m1=(size-1)/2;
        if(size%2==0){
            int m2=m1+1;
            return (float)(ls.get(m1)+ls.get(m2))/2;
        }else{
            return ls.get(m1);
        }
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for (int i = 0; i < as.length; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
