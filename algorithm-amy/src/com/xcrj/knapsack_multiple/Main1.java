package com.xcrj.knapsack_multiple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多重背包
 * 每个物品有限个
 * 可以转换为0-1背包
 */
public class Main1 {
    // 方法1，转换为0-1背包
    public static void testMultiPack1() {
        List<Integer> weight = new ArrayList<>(Arrays.asList(1, 3, 4));
        List<Integer> value = new ArrayList<>(Arrays.asList(15, 20, 30));
        List<Integer> nums = new ArrayList<>(Arrays.asList(2, 3, 2));
        int bagSize = 10;

        for (int i = 0; i < nums.size(); i++) {//索引
            // nums.get(i)-1 总共3件
            for (int j = 0; j < nums.get(i) - 1; j++) {//数量
                weight.add(weight.get(i));
                value.add(value.get(i));
            }
        }

        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.size(); i++) {
            for (int j = bagSize; j >= weight.get(i); j--) {//01背包倒序
                dp[j] = Math.max(dp[j], value.get(i) + dp[j - weight.get(i)]);
            }
        }

        System.out.println(dp[bagSize]);
        ;
    }

    //同一个物品操作有限次
    public static void testMultiPack2() {
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        int[] nums = new int[]{2, 3, 2};
        int bagSize = 10;

        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                for (int k = 1; k <= nums[i] && j - k * weight[i] >= 0; k++) {
                    dp[j] = Math.max(dp[j], k * value[i] + dp[j - k * weight[i]]);
                }
            }
        }

        System.out.println(dp[bagSize]);
    }

    public static void main(String[] args) {
        testMultiPack1();
        testMultiPack2();
    }
}
