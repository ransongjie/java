package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 041. 滑动窗口的平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 * 实现 MovingAverage 类：
 * - MovingAverage(int size) 用窗口大小 size 初始化对象。
 * - double next(int val)成员函数 next每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 * <p>
 * 问题本质：滑动窗口本质就是定长队列
 */
public class Solution41 {

    /**
     * 使用java Queue
     */
    class MovingAverage1 {
        Queue<Integer> que;
        int limit;
        double sum;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage1(int size) {
            que = new ArrayDeque<>();
            this.limit = size;
            this.sum = 0;
        }

        /**
         * sum统计队列中的元素之和
         * - 出队：sum减去出队值
         * - 入队：sum加上入队值
         */
        public double next(int val) {
            // 对列满，对头出队
            if (this.limit == this.que.size()) {
                // ！poll()会移除头头元素，element()不会移除对头元素
                int out = this.que.poll();
                this.sum -= out;
            }

            // 队列未满，往队列中加入数据
            // ！offer()适用队列长度不固定的队列，add()适用队列长度固定的队列，本题的队列长度会变化
            this.que.offer(val);
            sum += val;
            return sum / this.que.size();
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
            // size+1: 因为front，指向对头元素的前一个位置，浪费1个空间区分队列满和队列空
            this.que = new int[size+1];
            this.front = 0;
            this.rear = 0;

            this.limit = size+1;
            this.sum = 0;
        }

        /**
         * sum统计队列中的元素之和
         * - 出队：sum减去出队值
         * - 入队：sum加上入队值
         */
        public double next(int val) {
            // 对列满，对头出队
            if ((rear + 1) % this.limit == front) {
                this.front = (this.front + 1) % this.limit;
                int out = que[front];
                this.sum -= out;
            }

            // 队列未满，往队列中加入数据
            this.rear = (this.rear + 1) % this.limit;
            this.que[rear] = val;
            this.sum += val;
            return this.sum / ((this.limit + this.rear - this.front) % this.limit);
        }
    }

    public static void main(String[] args) {
        Solution41 solution41 = new Solution41();
        MovingAverage2 movingAverage2 = solution41.new MovingAverage2(2);
        System.out.println(movingAverage2.next(2));
        System.out.println(movingAverage2.next(2));
        System.out.println(movingAverage2.next(4));
    }

}
