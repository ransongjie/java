package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 006. 从已升序排序数组中寻找两个数，他们的和为target
 * - 数组中只有一对符合条件的数字。
 * - 同一个数字不能使用两次。
 */
public class Solution6 {
    /**
     * 二分查找
     * - target-numbers[i]是想要寻找的值
     * - 已经找过的值不再找
     * 
     * @param numbers
     * @param target
     * @return new int[0] 空数组
     */
    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            // xcrj 在剩余的序列中寻找[i+1,len-1]
            int t = binarySearch(numbers, target - numbers[i], i+1, numbers.length - 1);
            if (-1 != t) {
                return new int[] { i, t };
            }
        }

        return new int[0];
    }

    /**
     * 二分查找
     * - 寻找值等于中间值返回元素索引
     * - 寻找值大于中间值往右侧寻找
     * - 寻找值小于中间值往左侧寻找
     * 
     * @param numbers
     * @param x
     * @param start
     * @param end
     * @return -1表示没有找到
     */
    public int binarySearch(int[] numbers, int x, int start, int end) {
        int i = start;
        int j = end;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (x == numbers[mid]) {
                return mid;
            }

            if (x > numbers[mid]) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 双指针，反向指针
     * - 已知是升序序列
     * - i指针从左往右，j指针从右往左
     * - 元素i+元素j<target，i往右移动
     * - 元素i+元素j>target，j往左移动
     * @param numbers
     * @param target
     * @return new int[0]空数组
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i=0;
        int j=numbers.length-1;
        // xcrj 双指针反向移动停止条件
        while(i<j){
            if(target==numbers[i]+numbers[j]){
                return new int[]{i,j};
            }

            if(target>numbers[i]+numbers[j]){
                i++;
            }else{
                j--;
            }
        }

        return new int[0];
    }
}
