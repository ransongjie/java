package com.xcrj.permutation;

/**
 * https://leetcode.cn/problems/next-permutation/
 * 下一个排列
 * 字典序的下一个排列
 * <p>
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，
 * 如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例子
 * arr = [1,2,3] 的下一个排列是 [1,3,2]
 * arr = [2,3,1] 的下一个排列是 [3,1,2]
 * arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 */
public class Main2 {
    /**
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        //逆序原因：万、千、百、十、个
        //逆序，找到第1对降序的相邻元素
        int smallerIdx = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                smallerIdx = i;
                break;
            }
        }
        if (smallerIdx == -1) {
            //[3,2,1]>>[1,2,3]
            reverse(nums, 0, nums.length - 1);
            return;
        }
        //逆序，找到 首次大于nums[firstDownIdx] 的元素
        int biggerIdx = -1;
        for (int i = nums.length - 1; i >= smallerIdx; i--) {
            if (nums[i] > nums[smallerIdx]) {
                biggerIdx = i;
                break;
            }
        }
        //[1,2,5,4,3]>>[1,3,5,4,2]>>[1,3,2,4,5]
        swap(nums, smallerIdx, biggerIdx);
        reverse(nums, smallerIdx + 1, nums.length - 1);//smallerIdx+1
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
