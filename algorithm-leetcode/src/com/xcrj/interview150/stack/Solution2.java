package com.xcrj.interview150.stack;

import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/min-stack
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class Solution2 {
}

/**
 * 需要辅助栈空间
 */
class MinStack {
    Stack<Integer> valStack;
    Stack<Integer> minStack;

    public MinStack() {
        valStack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    /**
     * 每次入栈都将最小值放入minStack的栈顶
     *
     * @param val
     */
    public void push(int val) {
        valStack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    /**
     * 出栈时都出栈
     */
    public void pop() {
        valStack.pop();
        minStack.pop();
    }

    /**
     * 栈顶元素
     *
     * @return
     */
    public int top() {
        return valStack.peek();
    }

    /**
     * 栈顶最小值
     *
     * @return
     */
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * 栈中保存差值
 * 不用辅助栈空间
 */
class MinStack2 {
    //diff=val-preMin
    Stack<Long> diffStack;
    //每次入栈时记录最小值
    long min;

    public MinStack2() {
        diffStack = new Stack<>();
    }

    public void push(int val) {
        //栈空 min初始值=第1个入栈的val
        if (diffStack.isEmpty()) {
            min = val;
            diffStack.push(val - min);
            return;
        }
        /**
         * !!!
         * 若val>min，min不变，diff>0, val=diff+min
         * 若val<=min, min=val, diff<=0, val=min，diff=val-preMin=min=preMin, preMin=min-diff
         */
        diffStack.push((long) val - min);
        min = Math.min(min, (long) val);
    }

    public void pop() {
        long diff = diffStack.pop();
        if (diff < 0) {//push时更新过min，需要将min更新回来
            min = min - diff;
        }
    }

    public int top() {
        if (diffStack.peek() <= 0) return (int) min;
        else return (int) (min + diffStack.peek());
    }

    public int getMin() {
        return (int) min;
    }
}