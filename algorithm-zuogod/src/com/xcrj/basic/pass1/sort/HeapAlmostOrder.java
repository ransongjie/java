package com.xcrj.basic.pass1.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

//几乎有序的数组，元素在数组中的移动距离<k
public class HeapAlmostOrder {
    public static void main(String[] args) {
        int[] as={5,1,2,3,4,0,6,7,8,9};
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        int k=6;
        int i = 0;
        int j=0;
        for (; i < as.length; i++) {
            if(i<k) queue.offer(as[i]);//k个元素先入堆
            else{//剩余元素，入堆1个 出堆顶1个
                queue.offer(as[i]);
                as[j++]=queue.poll();
            }
        }
        for(i=0;i<queue.size();i++){//堆中剩余元素
            as[j++]=queue.poll();
        }
        System.out.println(Arrays.toString(as));
    }
}
