package com.xcrj.offer_special.pass1;

import java.util.Stack;

/**
 * 剑指 Offer II 037. 小行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 */
public class Solution37 {

    /**
     * asteroids中小小行星只有在 左边为正数 右边为负数的情况下才会相撞
     * ！！！从左往右一次尝试两个小行星是否能够相撞
     * ！！！循环的下1个小行星与栈顶元素相撞，栈存储存活的小行星
     * - 能不能相撞：循环的下1个小行星<0 栈顶小行星>0
     * - 相撞之后是否存活：|循环的下1个小行星|>|栈顶小行星|
     * - 如果相撞之后存活，栈顶元素出栈，继续尝试相撞
     * - 如果相撞之后没有存活，不再继续尝试相撞
     * <p>
     * 使用栈存储还存活的小行星
     */
    public int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            boolean alive = true;
            // 相撞，循环下1个小行星<0 栈顶小行星>0
            // 当小行星存活&&小行星<0往左运动&&栈有值&&栈顶小行星>0往右运动
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                // 循环下1个小行星 大于才存活，继续尝试相撞
                alive = -asteroid > stack.peek();
                // 循环下1个小行星 大于等于 栈顶小行星都爆炸
                if (-asteroid >= stack.peek()) {
                    stack.pop();
                }
            }
            // 存活，栈中存储存活的小行星
            if (alive) {
                stack.push(asteroid);
            }
        }
        // 构建结果
        int[] rs = new int[stack.size()];
        for (int i = rs.length - 1; i > -1; i--) {
            rs[i] = stack.pop();
        }
        return rs;
    }

    /**
     *  处理存活小行星
     *  - 使用栈存储
     *
     *  处理相撞小行星
     *  - 什么时候会能够相撞：右边小行星往左走&&左边的小行星往右走
     *  - 相撞之后怎么处理：爆炸或者存活
     */
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            boolean alive = true;
            /**
             * 处理相撞小行星
             */
            while (alive && !stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                // 处理爆炸
                int speak=stack.peek();
                if (-asteroid >= speak) {
                    stack.pop();
                }
                // 处理存活
                alive = -asteroid > speak;
                // 存活继续相撞
            }

            // 处理存活小行星
            if (alive) {
                stack.push(asteroid);
            }
        }

        // 构建结果
        int[] rs = new int[stack.size()];
        for (int i = rs.length - 1; i > -1; i--) {
            rs[i] = stack.pop();
        }
        return rs;
    }
}
