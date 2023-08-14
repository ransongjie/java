package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/jump-game-ii/?envType=study-plan-v2&envId=top-interview-150
 * 跳跃游戏，一定能跳跃到终点，跳跃到终点的最少次数
 */
public class Solution05 {
    public int jump(int[] as) {
        int step = 0, cur = 0, nxt = 0;
        for (int i = 0; i < as.length; i++) {
            //curRange不能覆盖则需要跳一次
            if (cur < i) {
                step++;
                cur = nxt;
            }
            //每次更新能够覆盖的范围nxtRange
            nxt = Math.max(nxt, i + as[i]);
        }
        return step;
    }
}
