package com.xcrj.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/target-sum/submissions/
 * 494. 目标和
 */
public class Main10 {

    public int findTargetSumWays(int[] nums, int target) {
        // 01背包应用之“有多少种不同的填满背包最大容量的方法“
        // 易于理解的二维数组解法及详细注释
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // 注意nums[i] >= 0的题目条件，意味着sum也是所有nums[i]的绝对值之和
        // 这里保证了sum + target一定是大于等于零的，也就是left大于等于零（毕竟我们定义left大于right）
        if (sum < Math.abs(target)) {
            return 0;
        }

        // 利用二元一次方程组将left用target和sum表示出来（替换掉right组合），详见代码随想录对此题的分析
        // 如果所求的left数组和为小数，则作为整数数组的nums里的任何元素自然是没有办法凑出这个小数的
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        int left = (sum + target) / 2;

        // dp[i][j]：遍历到数组第i个数时， left为j时的能装满背包的方法总数
        int[][] dp = new int[nums.length][left + 1];

        // 初始化最上行（dp[0][j])，
        // 当nums[0] == j时（注意nums[0]和j都一定是大于等于零的，因此不需要判断等于-j时的情况），
        // 有唯一一种取法可取到j，dp[0][j]此时等于1
        // 其他情况dp[0][j] = 0
        // java整数数组默认初始值为0
        for (int j = 0; j <= left; j++) {
            if (nums[0] == j) {
                dp[0][j] = 1;
            }
        }

        // 初始化最左列（dp[i][0])
        // 当从nums数组的索引0到i的部分有n个0时（n > 0)，每个0可以取+/-，因此有2的n次方中可以取到j = 0的方案
        // n = 0说明当前遍历到的数组部分没有0全为正数，因此只有一种方案可以取到j = 0（就是所有数都不取）
        int numZeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                numZeros++;
            }
            dp[i][0] = (int) Math.pow(2, numZeros);
        }

        // 递推公式分析：
        // 当nums[i] > j时，这时候nums[i]一定不能取，所以是dp[i - 1][j]种方案数
        // nums[i] <= j时，num[i]可取可不取，因此方案数是dp[i - 1][j] + dp[i - 1][j - nums[i]]
        // 由递推公式可知，先遍历i或j都可
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= left; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                }
            }
        }

        // 打印dp数组
        // for(int i = 0; i < nums.length; i++) {
        //     for(int j = 0; j <= left; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println("");
        // }

        return dp[nums.length - 1][left];
    }

    /**
     * @param nums   元素非负
     * @param target 可以为负
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        /**
         * 问题转换
         * - 一定有 left组合 - right组合 = target, right可以为0
         * - left - (sum - left) = target 推导出 tgt=left = (target + sum)/2 ，装满容量为 left 的背包，有几种方法
         * 背包：tgt
         * 物品：nums中的元素值
         * - 重量：nums中的元素值
         * - 价值：nums中的元素值
         * 物品要么选要么不选只有01两种状态
         * 物品只能选1次
         */
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum) return 0;
        // (target + sum)/2是小数，从nums中选不能组合成小数
        if (((target + sum) & 1) == 1) return 0;
        int tgt = ((target + sum) >> 1);
        // ！！！保证大于0
        tgt = tgt < 0 ? -tgt : tgt;
        // dp数组
        int[] dp = new int[tgt + 1];
        // 初始状态, 没有重量，1种方法，什么都不选
        dp[0] = 1;
        // 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = tgt; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        // 方法数
        return dp[tgt];
    }
}
