package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 090. 环形房屋偷盗
 * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * 分析：
 * - 如果偷窃了第一间房屋，则不能偷窃最后一间房屋，因此偷窃房屋的范围是第一间房屋到最后第二间房屋；
 * - 如果偷窃了最后一间房屋，则不能偷窃第一间房屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
 */
public class Solution90 {

    /**
     * 动态规划+滚动数组
     * 滚动数组：使用prev，curr，next交替记录值
     */
    public int rob(int[] nums) {
        if (null == nums) {
            return 0;
        }
        if (0 == nums.length) {
            return 0;
        }
        if (1 == nums.length) {
            return nums[0];
        }
        // 只有两间房时，不用考虑偷盗房屋不能首尾相连的问题
        if (2 == nums.length) {
            return Math.max(nums[0], nums[1]);
        }

        /**
         * 把能防止环形偷到的方式都偷一遍 取最大值
         * - 多于两间房 需要考虑偷盗的位置 防止出现环形相连偷盗
         * - 如果偷窃了第一间房屋，则不能偷窃最后一间房屋，因此偷窃房屋的范围是第一间房屋到最后第二间房屋；
         * - 如果偷窃了最后一间房屋，则不能偷窃第一间房屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
         */
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    /**
     * 环形偷盗
     *
     * @param nums
     * @param start 开始偷盗房间
     * @param end   结束偷盗房间
     * @return 从start到end偷盗的最大价值
     */
    private int robRange(int[] nums, int start, int end) {
        // prev 之前总最大价值
        // 只有一间房取
        int prev = nums[start];
        // curr 当前总最大价值
        // 有两间房取最大值
        int curr = Math.max(nums[start], nums[start + 1]);
        // 前面2间房已经在prev和curr中了
        for (int i = start + 2; i <= end; i++) {
            // 有多间房，dp[i]=max{dp[i-2]+nums[i],dp[i-1]},不能偷盗连续的两间房
            int next = Math.max(prev + nums[i], curr);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
