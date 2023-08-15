package com.xcrj.interview150.stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation
 * <p>
 * 逆波兰表达式求值
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 注意：
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 * <p>
 * 后缀表达式求值
 */
public class Solution3 {
    /**
     * 使用一个栈存储操作数，从左到右遍历逆波兰表达式，进行如下操作：
     * - 如果遇到操作数，则将操作数入栈
     * - 如果遇到运算符，则将两个操作数出栈。其中先出栈的是右操作数，后出栈的是左操作数，使用运算符对两个操作数进行运算，将运算得到的新操作数入栈。
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n = tokens.length, a, b;
        for (String token :
                tokens) {
            if (isNumber(token)) {
                stack.push(Integer.valueOf(token));
            } else {
                b = stack.pop();
                a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
