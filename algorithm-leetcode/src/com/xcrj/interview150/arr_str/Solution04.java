package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/jump-game/solutions/24322/55-by-ikaruga
 * 跳跃游戏，能否跳到终点
 */
public class Solution04 {
    public static void main(String[] args) {
        Solution04 s04 = new Solution04();
        int[] as = {3, 2, 1, 0, 4};
        boolean r = s04.canJump(as);
        System.out.println(r);
    }

    /**
     * @param as
     * @return
     */
    public boolean canJump(int[] as) {
        if (as == null) return false;
        int n = as.length;
        if (n == 0) return false;
        if (n == 1) return true;

        int maxRange = 0;
        for (int i = 0; i <= maxRange; i++) {//<= maxRange
            maxRange = Math.max(maxRange, i + as[i]);
            //在最大能覆盖范围内，寻找能够超过终点的idx
            if (maxRange >= n - 1) return true;
        }
        return false;
    }
}
