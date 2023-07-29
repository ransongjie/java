package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 042. 最近请求次数
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * 请实现 RecentCounter 类：
 * - RecentCounter() 初始化计数器，请求数为 0 。
 * - int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 * 求在 3000毫秒时间窗口内有多少次请求
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
            this.que = new ArrayDeque<>();
        }

        public int ping(int t) {
            // 入队
            this.que.offer(t);
            // 队列中保留[t-3000, t]的数据
            while (this.que.peek() < t - 3000) {
                this.que.poll();
            }
            // 1个t1个请求，que中t的数量就是请求的数量
            return this.que.size();
        }
    }

    /**
     * 循环队列
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
            // 极限情况下每毫秒发送1刺请求。3000+1：front指向对头的前1个结点为了区分队列满和队列空
            this.que = new int[3001];
            this.front = 0;
            this.rear = 0;
        }

        public int ping(int t) {
            // 入队
            this.rear = (this.rear + 1) % this.que.length;
            this.que[this.rear] = t;
            // 对头不在t-3000范围内则出队
            while (this.que[this.front] < t - 3000) {
                this.front = (this.front + 1) % this.que.length;
            }
            // 1个t1个请求，que中t的数量就是请求的数量
            return (this.que.length + this.rear - this.front) % this.que.length;
        }
    }

    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        RecentCounter2 recentCounter2 = solution42.new RecentCounter2();
        System.out.println(recentCounter2.ping(1));
        System.out.println(recentCounter2.ping(100));
        System.out.println(recentCounter2.ping(3001));
        System.out.println(recentCounter2.ping(3002));
        System.out.println(recentCounter2.ping(3003));
    }
}
