package com.xcrj.offer_special.pass1;

import java.util.Stack;

/**
 * 剑指 Offer II 036. 后缀表达式
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 */
public class Solution36 {
    /**
     * 后缀表达式求值
     * - 遇到操作数入栈
     * - 遇到操作符出栈两个操作数，先出栈的是右操作数
     * - 计算结果入栈
     * - 计算完毕栈中只有1个元素是最终的结果
     * <p>
     * 使用java提供的栈
     */
    public int evalRPN1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            if (isNumber(t)) {
                stack.push(new Integer(t));
            } else {
                int r = stack.pop();
                int l = stack.pop();
                switch (t) {
                    case "+":
                        stack.push(l + r);
                        break;
                    case "-":
                        stack.push(l - r);
                        break;
                    case "*":
                        stack.push(l * r);
                        break;
                    case "/":
                        stack.push(l / r);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public boolean isNumber(String t) {
        if ("+".equals(t)) return false;
        if ("-".equals(t)) return false;
        if ("*".equals(t)) return false;
        if ("/".equals(t)) return false;
        return true;
    }

    /**
     * 使用数组栈
     * - 栈空 top=-1
     * - 栈满 top=length-1
     * - 入栈 top++
     * - 出栈 top--
     * <p>
     * 数组栈大小
     * - n个操作数，n-1个操作符，共2*n-1个字符
     * - 遇到操作数入栈，栈中字符+1；遇到操作符出栈2个操作数入栈1个操作数，栈中字符-1；因此有n个字符的后缀表达式 数组栈数组长度为(n+1)/2就够了，前全是操作数
     */
    public int evalRPN2(String[] tokens) {
        int[] stack = new int[(tokens.length + 1) / 2];
        int top = -1;
        for (String t : tokens) {
            if (isNumber(t)) {
                stack[++top] = new Integer(t);
            } else {
                int r = stack[top--];
                int l = stack[top--];
                switch (t) {
                    case "+":
                        stack[++top] = l + r;
                        break;
                    case "-":
                        stack[++top] = l - r;
                        break;
                    case "*":
                        stack[++top] = l * r;
                        break;
                    case "/":
                        stack[++top] = l / r;
                        break;
                }
            }
        }

        return stack[top--];
    }
}
