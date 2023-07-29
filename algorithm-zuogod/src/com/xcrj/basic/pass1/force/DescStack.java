package com.xcrj.basic.pass1.force;

import java.util.Stack;

/**
 * 逆序栈，只能使用递归函数 
 * 每次取出底部元素，再压入栈中
 */
public class DescStack {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(3);stack.push(2);stack.push(1);
        reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static void reverse(Stack<Integer> stack) {
        int size=stack.size();
        for (int i = 0; i < size; i++) {
            int bottom=getBottom(stack);
            stack.push(bottom);
        }
    }

    public static int getBottom(Stack<Integer> stack) {
        int r=stack.pop();//
        if(stack.isEmpty()) return r;
        int last=getBottom(stack);//
        stack.push(r);
        return last;
    }
}
