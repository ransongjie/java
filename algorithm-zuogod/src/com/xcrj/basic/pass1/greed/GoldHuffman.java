package com.xcrj.basic.pass1.greed;

import java.util.PriorityQueue;

//金条分割 哈夫曼编码
public class GoldHuffman {
    public static void main(String[] args) {
        int[] as={10,20,30};
        int r=goldHuffman(as);
        System.out.println(r);
    }

    //所需最少成本/铜板
    public static int goldHuffman(int[] as) {
        if(as==null||as.length==0||as.length==1) return 0; 
        PriorityQueue<Integer> pque=new PriorityQueue<>((o1,o2)->o1-o2);
        for (int a : as) {
            pque.add(a);
        }
        //
        int r=0;
        while(pque.size()!=1){
            int a=pque.poll();
            int b=pque.poll();
            r+=a;
            r+=b;
            pque.offer(a+b);
        }
        //
        return r;
    }
}
