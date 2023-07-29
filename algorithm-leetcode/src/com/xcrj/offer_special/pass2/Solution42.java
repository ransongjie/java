package com.xcrj.offer_special.pass2;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 042. 最近请求次数
 * [t-3000,t]时间段内的请求数量
 */
public class Solution42 {
    /**
     * 使用java队列
     * 在时间 t 添加一个新请求
     * 返回在 [t-3000, t] 内发生的请求数
     */
    class RecentCounter1 {
        Queue<Integer> que;

        public RecentCounter1() {
            que=new ArrayDeque<>();
        }

        public int ping(int t) {
            que.offer(t);
            // 时间必须在[t-3000,t]时间段内
            while(que.peek()<t-3000){
                que.poll();
            }

            return que.size();
        }
    }

    /**
     * 循环队列 无法解决这个问题 因为循环队列会对满
     * front指向对头的前1个结点为了区分队列满和队列空
     * rear指向队尾结点
     * 开始：front=0，rear=0；
     * 入队：rear=(rear+1)%queMaxSize
     * 出队：front=(front+1)%queMaxSize
     * 队列满：(rear+1)%queMaxSize==front;
     * 队列空：front==rear
     * 队列元素个数：(queMaxSize+rear-front)%queMaxSize
     */
    class RecentCounter2 {
        int[] que;
        int front;
        int rear;

        public RecentCounter2() {
            // xcrj todo 3002
            que=new int[3001];
            front=0;
            rear=0;
        }

        public int ping(int t) {
            rear=(rear+1)%que.length;
            que[rear]=t;
            // xcrj (front+1)%que.length 才是对头元素
            while(que[(front+1)%que.length]<t-3000){
                front=(front+1)%que.length;
            }
            return (que.length+rear-front)%que.length;
        }
    }

    public static void main(String[] args) {
        Solution42 solution42=new Solution42();
        RecentCounter2 recentCounter2=solution42.new RecentCounter2();
        for(int i=1;i<3002;i++){
            int len=recentCounter2.ping(i);
            if(i==3001){
                System.out.println(len);
            }
        }
    }
}
