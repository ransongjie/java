package com.xcrj.offer_special.pass2;

import java.util.Stack;

/**
 * 剑指 Offer II 036. 后缀表达式
 * 有效的算符包括 +、-、*、/
 */
public class Solution36 {
    /**
     * 使用java提供的栈
     * 后缀表达式求值
     * - 遇到操作数入栈
     * - 遇到操作符出栈两个操作数，先出栈的是右操作数
     * - 计算结果入栈
     * - 计算完毕栈中只有1个元素是最终的结果
     * @param tokens
     * @return
     */
    public int evalRPN1(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String token:tokens){
            if(numbered(token)){
                stack.push(new Integer(token));
            }else{
                int r=stack.pop();
                int l=stack.pop();
                switch(token){
                    case "+":
                    stack.push(l+r);
                    break;
                    case "-":
                    stack.push(l-r);
                    break;
                    case "*":
                    stack.push(l*r);
                    break;
                    case "/":
                    stack.push(l/r);
                    break;
                }
            }
        }

        return stack.pop();
    }

    /**
     * 是数字
     * @param t
     * @return
     */
    public boolean numbered(String t) {
        boolean numbered=true;
        switch(t){
            case "+":
            numbered=false;
            break;
            case "-":
            numbered=false;
            break;
            case "*":
            numbered=false;
            break;
            case "/":
            numbered=false;
            break;
        }
        return numbered;
    }

    /**
     * 数组栈
     * - 栈空：top=-1
     * - 栈满：top=size-1
     * - 入栈：stack[++top]
     * - 出栈：stack[top--]
     * - 数组栈大小，n个操作数+n-1个操作符=2*n-1，
     * 遇到操作数入栈，栈中字符数+1，遇到操作符出栈2个操作数，入栈1个结果，栈中字符数-1
     * 长度为k的后缀表达式，最多需要把所有操作数都装入，即需要(k+1)/2的空间
     * xcrj 遇到操作数栈中字符数量才会增加，那么最多会遇到多少操作数
     * @param tokens
     * @return
     */
    public int evalRPN2(String[] tokens) {
        int top=-1;
        int[] stack=new int[(tokens.length+1)/2];
        for(String token:tokens){
            if(numbered(token)){
                stack[++top]=new Integer(token);
            }else{
                int r=stack[top--];
                int l=stack[top--];
                switch(token){
                    case "+":
                    stack[++top]=l+r;
                    break;
                    case "-":
                    stack[++top]=l-r;
                    break;
                    case "*":
                    stack[++top]=l*r;
                    break;
                    case "/":
                    stack[++top]=l/r;
                    break;
                }
            }
        }
        return stack[top--];
    }
}
