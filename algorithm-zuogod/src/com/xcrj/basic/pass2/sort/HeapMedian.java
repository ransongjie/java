package com.xcrj.basic.pass2.sort;
/**
 * 获取数据流中的中位数
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HeapMedian {
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
                    System.out.println(j+": "+as[j]);
                    System.out.println(median1);
                    System.out.println(median2);
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * left median right
     * 小的数在大根堆 中位数 大的数在小根堆
     * 两个队列
     * 队列为空
     * 对头大小
     * 元素数量
     * @param a
     */
    static PriorityQueue<Integer> smlpque=new PriorityQueue<>((o1,o2)->o1-o2);
    static PriorityQueue<Integer> bigpque=new PriorityQueue<>((o1,o2)->o2-o1);
    public static float heapMedian(int a) {
        if(bigpque.isEmpty()){
            bigpque.offer(a);
        }else{
            if(a>bigpque.peek()){
                smlpque.offer(a);
            }else{
                bigpque.offer(a);
            }

            if(bigpque.size()-smlpque.size()>=2){
                smlpque.offer(bigpque.poll());
            }
            if(smlpque.size()-bigpque.size()>=2){
                bigpque.offer(smlpque.poll());
            }
        }

        int n1=bigpque.size();
        int n2=smlpque.size();
        if((n1+n2)%2==0){
            return (bigpque.peek()+smlpque.peek())/2.0f;
        }
        if(n1>n2){
            return bigpque.peek();
        }
        if(n2>n1){
            return smlpque.peek();
        }
        return 0.f;
    }

    static List<Integer> list=new ArrayList<>();
    public static float cp(int a) {
        list.add(a);
        Collections.sort(list);
        if(list.size()%2==0){
            int n1=list.get((list.size()/2)-1);
            int n2=list.get(list.size()/2);
            return (n1+n2)/2.0f;
        }
        return list.get(list.size()/2);
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
