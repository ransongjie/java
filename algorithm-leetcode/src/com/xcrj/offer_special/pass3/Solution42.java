package com.xcrj.offer_special.pass3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 042. 最近请求次数
 * [t-3000,t]时间段内的请求数量
 */
public class Solution42 {
    class RecentCounter {
        Queue<Integer> que;
        public RecentCounter() {
            this.que=new ArrayDeque<>();
        }
        public int ping(int t) {
            //入队
            que.offer(t);
            //队头
            while(que.peek()<t-3000){
                //出队
                que.poll();
            }
            return que.size();
        }
    }
}
