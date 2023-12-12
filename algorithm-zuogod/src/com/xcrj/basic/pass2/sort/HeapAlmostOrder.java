package com.xcrj.basic.pass2.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 几乎有序的数组，每个数字到自己的最终位置移动距离不超过k
 */
public class HeapAlmostOrder {
    public static void main(String[] args) {
        int[] as={5,1,2,3,4,0,6,7,8,9};
        int k=6;
        heapAlmostOrder(as, k);
        System.out.println(Arrays.toString(as));
    }

    public static void heapAlmostOrder(int[]as,int k) {
        PriorityQueue<Integer> pque=new PriorityQueue<>();
        int j=0;
        for (int i = 0; i < as.length; i++) {
            // 大小为k的堆
            if(i<k) pque.offer(as[i]);
            else{
                pque.offer(as[i]);
                as[j++]=pque.poll();
            }
        }
        
        while(!pque.isEmpty()){
            as[j++]=pque.poll();
        }
    }
}
