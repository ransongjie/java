package com.xcrj.offer_special.pass2;

import java.util.Stack;

/**
 * 剑指 Offer II 037. 小行星碰撞
 * asteroids，表示一组小行星。
 * 数组中的每一个元素，
 * - 其绝对值表示小行星的大小，
 * - 正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
 * - 每一颗小行星以相同的速度移动。
 * 碰撞规则：
 * - 两个行星相互碰撞，较小的行星会爆炸。
 * - 如果两颗行星大小相同，则两颗行星都会爆炸。
 * - 两颗移动方向相同的行星，永远不会发生碰撞。
 */
public class Solution37 {

    public int[] asteroidCollision1(int[] asteroids) {
        // 存活的小行星放入栈中
        Stack<Integer> stack=new Stack<>();
        // 判断每一个小行星
        for(int asteroid:asteroids){
            boolean alive=true;
            // 能不能相撞
            // 当小行星存活&&小行星<0往左运动&&栈有值&&栈顶小行星>0往右运动
            // xcrj peek() 不会移除栈顶元素，只是查看栈顶元素
            while(alive&&asteroid<0&&!stack.isEmpty()&&stack.peek()>0){
                // 相撞之后的结果
                if(-asteroid>stack.peek()){
                    stack.pop();
                    continue;
                }
                if(-asteroid==stack.peek()){
                    alive=false;
                    stack.pop();
                    continue;
                }
                if(-asteroid<stack.peek()){
                    alive=false;
                    continue;
                }
            }
            
            if(alive){
                stack.push(asteroid);
            }
        }

        int[] rs=new int[stack.size()];
        // 将栈底放到数组开头
        for(int i=rs.length-1;i>=0;i--){
            rs[i]=stack.pop();
        }
        return rs;
    }
}
