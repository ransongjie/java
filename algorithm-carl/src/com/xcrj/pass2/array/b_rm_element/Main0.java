package com.xcrj.pass2.array.b_rm_element;

/**
 * https://leetcode.cn/problems/remove-element/
 * 移除元素
 */
public class Main0 {
    /**
     * @param nums
     * @param val
     * @return 新数组的右边界
     */
    public int removeElement(int[] nums, int val) {
        //不需要排序
        int border = 0, i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[border++] = nums[i];
            }
        }
        return border;
    }

    /**
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {
        //先排序
        int lborder = 0, rborder = nums.length - 1;
        while (lborder <= rborder) {
            while (lborder <= rborder && nums[lborder] != val) lborder++;
            while (lborder <= rborder && nums[rborder] == val) rborder--;
            if (lborder <= rborder) {
                nums[lborder++] = nums[rborder--];
            }
        }
        return lborder;
    }
}
