package com.xcrj.basic.pass2.greed;

import java.util.PriorityQueue;

/**
 * 金条分割所需最少成本
 * int[] as={10,20,30}; 总和是金条长度，元素值是子金条长度和切割成本
 */
public class GoldSplitHuffman {
    public static void main(String[] args) {
        int[] as={10,20,30};
        int r=gold(as);
        System.out.println(r);
    }

    public static int gold(int[] costs) {
        if(costs==null||costs.length==0||costs.length==1) return -1;
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        for (int c : costs) {
            pq.offer(c);
        }
        int r=0;
        // 哈夫曼
        while(pq.size()!=1){
            int a=pq.poll();
            int b=pq.poll();
            pq.offer(a+b);
            r+=a;r+=b;
        }
        return r;
    }
}
