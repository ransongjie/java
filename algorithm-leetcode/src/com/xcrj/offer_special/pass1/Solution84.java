package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 * <p>
 * 分析：
 * - 先排序nums
 * - 打包插空法。
 * -- 把相等的元素打包法放到一起，插入其他不相等的元素中间
 * -- 相等的第1个元素放到什么位置，相邻的相等元素依次放到后面。
 * -- 相等的第1个元素被放到第i个空格中，相邻的相等元素从左往右依次被填到后续空格中。
 */
public class Solution84 {
    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    // 第j个元素已经被添加/填充到第i个空格
    boolean[] iAdded;

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 解空间树
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     * <p>
     * 本题目看做，往len长的空格中添加nums中剩余的元素
     * 考虑将第j个元素添加到第i个空格中（不能填已经填过的元素&&不能填相等的元素），填写完成尝试填写下一个位置i+1
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.iAdded = new boolean[nums.length];
        // 排序，保证相等元素放到一起，方便从左往右依次尝试填充时判断不能填相等的元素
        Arrays.sort(nums);
        backTrack(nums, 0);
        return rss;
    }

    /**
     * 过程
     * - i=len，填完了len个位置，找到可行解
     * - i<len，循环len次数，考虑将第j个元素添加到第i个空格中（不能填已经填过的元素&&不能填相等的元素），填写完成尝试填写下一个位置i+1
     *
     * @param nums
     * @param i    尝试填写第i个位置
     */
    private void backTrack(int[] nums, int i) {
        // i=len，填完了len个位置，找到可行解
        if (i == nums.length) {
            this.rss.add(new ArrayList<>(this.list));
            return ;
        }

        // 循环len次数，考虑将第j个元素添加到第i个空格中（不能填已经填过的元素&&不能填相等的元素），填写完成尝试填写下一个位置i+1
        for (int j = 0; j < nums.length; j++) {
            // 不能填已经填过的元素&&不能填相等的元素
            if (this.iAdded[j]) continue;
            /**
             * 相等的第1个元素被放到第i个空格中，相邻的相等元素从左往右依次被填到后续空格中。
             * - j>0,防止j-1<0。
             * - !this.iAdded[j-1]，前一个元素还没有填入空格时，不能把后一个元素填入空格
             */
            if (j > 0 && nums[j] == nums[j - 1] && !this.iAdded[j - 1]) continue;

            // 将第j个元素放到第i个空格中
            this.list.add(nums[j]);
            // 第j个元素已经被添加到第i个空格中
            this.iAdded[j] = true;
            // 处理下一个空格，i+1个空格
            backTrack(nums, i + 1);
            // 回溯，删除第i个空格上的第j个元素值
            this.list.remove(i);
            // 回溯，第j个元素没有被添加到第i个空格中
            this.iAdded[j] = false;
        }
    }
}
