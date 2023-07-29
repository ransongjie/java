package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 102. 加减的目标值
 * 给定一个正整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 */
public class Solution102 {

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * <p>
     * 解空间树
     * - 子集树：达到要求的子集，元素子集。选择某个元素/不选择某个元素
     * <p>
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     */
    public int findTargetSumWays1(int[] nums, int target) {
        // 第0个元素开始回溯，开始时没有回溯任何元素sum=0
        backTrack(nums, target, 0, 0);
        return count;
    }

    // 满足表达式=target条件的表达式个数
    int count = 0;

    /**
     * 回溯
     * 为第i个元素添加+号或-号组成表达式的值=target
     *
     * @param nums   待回溯数组
     * @param target 目标值
     * @param idx    回溯第idx个元素
     * @param sum    已经回溯的元素总和
     */
    private void backTrack(int[] nums, int target, int idx, int sum) {
        // 处理完数组中所有元素
        if (nums.length == idx) {
            // 满足表达式的值=target
            if (sum == target) this.count++;
            return;
        }
        // 未处理完数组中所有元素，继续回溯
        if (idx < nums.length) {
            // 将nums[index]前添加+号
            backTrack(nums, target, idx + 1, sum + nums[idx]);
            // 回溯回来，将nums[index]前添加-号
            backTrack(nums, target, idx + 1, sum - nums[idx]);
            return;
        }
    }

    /**
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * <p>
     * ！解法套路
     * 1. 定义动态规划数组
     * 2. 动态规划数组的边界条件
     * 3. 动态规划数组的状态转移方程
     * 4. 使用滚动数组进行空间压缩
     */
    public int findTargetSumWays2(int[] nums, int target) {
        // 求数组元素总和
        int sum = Arrays.stream(nums).sum();

        // 特殊情况：target应该要<=sum，当sum-target<0时，一种表达式值=target的情况都没有
        if (sum - target < 0) return 0;

        /**
         * 特殊情况：(sum-将被添加负号的元素和)=将被添加正号的元素和
         * (sum-将被添加负号的元素和)-将被添加负号的元素和=target
         * 将被添加负号的元素和=(sum-target)/2，将被添加负号的元素和 是 正整数，所以(sum-target)能整除2
         */
        if ((sum - target) % 2 != 0) return 0;

        /**
         * 动态规划数组，dp[从nums[:i]中选取若干元素（可以不选）][和等于j]=方案数
         * 在数组中构造表达式值=target 《(sum-将被添加负号的元素和)-将被添加负号的元素和=target，将被添加负号的元素和=(sum-target)/2》从数组中选取若干元素和等于 将被添加负号的元素和
         * 类似剑指 Offer II 101. 分割等和子集
         * +1，不能选取任何元素占用1个空间，和=0占用1个空间
         */
        int willneg = (sum - target) / 2;
        int[][] dp = new int[nums.length + 1][willneg + 1];

        // 边界条件，j=0，只有1个方案就是不选取任何元素
        dp[0][0] = 1;
        // 当j>0,i=0即没有任何元素可选时,不能满足 表达式值=j,所以dp[0][j]=0
        for (int j = 1; j < willneg + 1; j++) {
            dp[0][j] = 0;
        }

        // 当j>0&&i>0时，
        // 若j<nums[i]，一定不选取nums[i]，则dp[i][j]=dp[i-1][j]
        // 若j>=nums[i]，可选可不选取nums[i]，选取nums[i]，则dp[i][j]=dp[i-1][j-nums[i]]；不选nums[i]，则dp[i][j]=dp[i-1][j]
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 0; j < willneg + 1; j++) {
                // i-1，需要和nums[0]对比
                int num = nums[i - 1];
                // 若j<nums[i]，一定不选取nums[i]，则dp[i][j]=dp[i-1][j]
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                // 若j>=nums[i]，可选可不选取nums[i]，选取nums[i]，则dp[i][j]=dp[i-1][j-nums[i]]；不选nums[i]，则dp[i][j]=dp[i-1][j]
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                    continue;
                }
            }
        }

        return dp[nums.length][willneg];
    }

    /**
     * 空间压缩。
     * - dp[i][j] = dp[i - 1][j];
     * - dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
     * - 发现dp[i]只和dp[i-1]，当前行只与上一行有关
     */
    public int findTargetSumWays3(int[] nums, int target) {
        // 求数组元素总和
        int sum = Arrays.stream(nums).sum();

        if (sum - target < 0) return 0;

        if ((sum - target) % 2 != 0) return 0;

        //动态规划数组，从nums[:i]选择若干原始 dp[和等于j]=方案数
        int willneg = (sum - target) / 2;
        int[] dp = new int[willneg + 1];

        // 边界条件，第0行，j=0，只有1个方案就是不选取任何元素
        dp[0] = 1;

        // 当j>0,第0行，即没有任何元素可选时,不能满足 表达式值=j,所以[j]=0
        for (int j = 1; j < willneg + 1; j++) {
            dp[j] = 0;
        }

        for (int num : nums) {
            /**
             * 为什么倒序：若正序更新dp，在计算dp[j]值的时候，dp[j−nums[i]]已经被更新，不再是上一行的dp值。
             * 若正序
             * - dp[j−nums[i]]=dp[j−nums[i]] + dp[j-nums[i]-nums[i]]
             * - dp[j] = dp[j] + dp[j - nums[i]]; dp[j - nums[i]]已经被更新
             */
            for (int j = willneg; j >= num; j--) {
                // 若j<num，一定不选取num，则当前行dp[j]=上一行的dp[j]
                if (j < num) {
                    dp[j] = dp[j];
                    continue;
                }

                // 若j>=num，可选可不选取num，选取num，则 当前行dp[j]=前一行dp[j-nums[i]]；不选num，则 当前行dp[j]=前一行dp[j]
                if (j >= num) {
                    dp[j] = dp[j] + dp[j - num];
                    continue;
                }
            }
        }

        return dp[willneg];
    }
}
