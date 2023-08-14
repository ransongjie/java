package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * +-获得target的方法数
 */
public class A002TargetSum {
    /**
     * 暴力递归
     *
     * @param as     元素前添加+或-
     * @param target 目标值
     * @return
     */
    public static int targetSum(int[] as, int target) {
        return process(as, target, 0);
    }

    private static int process(int[] as, int rest, int i) {
        if (i == as.length) {
            return rest == 0 ? 1 : 0;
        }

        return process(as, rest - as[i], i + 1)
                + process(as, rest + as[i], i + 1);
    }

    /**
     * 记忆搜索
     *
     * @param as
     * @param target
     * @return
     */
    public static int targetSum2(int[] as, int target) {
        return process2(as, target, 0, new HashMap<>());
    }

    /**
     * 0~idx, =rest, ways=num
     * ...
     * 0~len, =0, ways=num
     *
     * @param as
     * @param rest
     * @param i
     * @param dp   Map<idx,Map<rest,num>> 0~idx, =rest, ways=num
     * @return
     */
    private static int process2(int[] as, int rest, int i
            , HashMap<Integer, HashMap<Integer, Integer>> dp) {
        //缓存命中与否
        if (dp.containsKey(i) && dp.get(i).containsKey(rest)) {
            return dp.get(i).get(rest);
        }

        int r = 0;
        if (i == as.length) {
            r = rest == 0 ? 1 : 0;
        } else {
            r = process2(as, rest - as[i], i + 1, dp)
                    + process2(as, rest + as[i], i + 1, dp);
        }

        if (!dp.containsKey(i)) {
            dp.put(i, new HashMap<>());
        }
        dp.get(i).put(rest, r);
        return r;
    }

    //优化 认为as中的所有元素都是非负
    public static int targetSum3(int[] as, int target) {
        int sum = 0;
        for (int a :
                as) {
            sum += a;
        }
        //第2个条件奇偶性不一致。第2个条件成了纯纯的背包问题
        //(positiveSum+negtiveSum+sum)>>1=positiveSum 背包问题
        return target > sum || ((sum ^ 1) ^ (target ^ 1)) != 0 ? 0 : process3(as, (target + sum) >> 1);
    }

    //2维动态规划 优化为1维动态规划 空间压缩技巧

    /**
     * 背包问题，从as[]中选物品=target的方法数
     * target=0, 方法数1种
     * target=i, 方法数
     * target=p, 方法数
     *
     * @param as
     * @param p  target
     * @return
     */
    private static int process3(int[] as, int p) {
        /**
         * dp[i][j] = max(dp[i−1][j], dp[i−1][j−w[i]]+v[i]) // j >= w[i]
         * dp[物品idx][限重j]=最大价值(不装入第i件物品,装入第i件物品(装入后限重减小))
         *
         * dp[i][j] = dp[i-1][j] + dp[i−1][j−w[i]]
         * dp[物品idx][限重j]=不装入第i件物品 + 装入第i件物品(装入后限重减小)
         *
         * dp[i][j]仅仅与上一层dp[i-1][0~j]相关
         * 防止dp[i][j]覆盖dp[i-1][j]，逆向枚举
         */
        int[] dp = new int[p + 1];
        dp[0] = 1;//target=0, 什么都不装1种方法
        for (int a : as) {
            for (int i = p; i >= a; i--) {
                dp[i] += dp[i - a];
            }
        }
        return dp[p];
    }
}
