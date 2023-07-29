package com.xcrj.offer_special.pass2;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 041. 滑动窗口的平均值
 * 实现 MovingAverage 类：
 * - MovingAverage(int size) 用窗口大小 size 初始化对象。
 * - double next(int val)成员函数 next每次调用的时候都会往滑动窗口增加一个整数，请计算并返回滑动窗口里所有数字的平均值。
 */
public class Solution41 {
    class MovingAverage1 {
        Queue<Integer> que;
        int limit;
        // xcrj double
        double sum;
        public MovingAverage1(int size) {
            this.que=new ArrayDeque<>();
            this.limit=size;
            this.sum=0;
        }

        public double next(int val) {
            if(this.limit==this.que.size()){
                // poll 丢弃对头
                int pol=this.que.poll();
                this.sum-=pol;
                this.que.offer(val);
                this.sum+=val;
                return this.sum/this.limit;
            }

            this.que.offer(val);
            this.sum+=val;
            return this.sum/this.que.size();
        }
    }

    /**
     * 循环队列
     * front：指向对头元素的前一个位置，浪费1个空间区分队列满和队列空
     * rear：指向队尾元素
     * 开始：front=0，rear=0；
     * 入队：rear=(rear+1)%queSize，que[rear]=val
     * 出队：front=(front+1)%queSize，val=que[front]
     * 队空：front==rear
     * 队满：(rear+1)%queSize==front
     * 队列长度：(queSize+rear-front)%queSize
     * 补充：queSize是固定的，queLen是变化的
     */
    class MovingAverage2 {
        int[] que;
        int front;
        int rear;
        int limit;
        double sum;

        public MovingAverage2(int size) {
            this.que=new int[size+1];
            this.limit=size+1;
            this.front=0;
            this.rear=0;
            this.sum=0.0;
        }

        public double next(int val) {
            if((this.rear+1)%this.limit==front){
                this.front=(this.front+1)%this.limit;
                int pol=this.que[this.front];
                this.sum-=pol;
                this.rear=(this.rear+1)%this.limit;
                this.que[this.rear]=val;
                this.sum+=val;
                // xcrj (this.limit+this.rear-this.front)%this.limit 不写limit
                return this.sum/((this.limit+this.rear-this.front)%this.limit);
            }

            this.rear=(this.rear+1)%this.limit;
            this.que[this.rear]=val;
            this.sum+=val;
            // xcrj 运算符优先级
            return this.sum/((this.limit+this.rear-this.front)%this.limit);
        }
    }
}
