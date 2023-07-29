package com.xcrj.offer_special.pass3;

/**
 * 二分查找
 * 双指针
 * 剑指 Offer II 006. 从已升序排序数组中寻找两个数，他们的和为target
 * - 数组中只有一对符合条件的数字。
 * - 同一个数字不能使用两次，已经找遍历过的数字不再使用
 */
public class Solution6 {
    /**
     * numbers已经有序，使用二分查找
     * - target-numbers[i]是想要寻找的值
     * - 已经找过的值不再找
     * 
     * @param numbers
     * @param target
     * @return new int[0] 空数组
     */
    public int[] twoSum1(int[] numbers, int target) {
        //遍历数组寻找 target-numbers[i]，已遍历过的数字不再使用
        for(int i=0;i<numbers.length;i++){
            int j=binarySearch(numbers, target-numbers[i], i+1, numbers.length-1);
            if(-1!=j){
                return new int[]{i,j};
            }
        }

        // xcrj java空数组
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
        int i=start;
        int j=end;
        while(i<=j){
            int mid=(i+j)/2;
            // xcrj 与中间索引值比较
            if(numbers[mid]==x){
                return mid;
            }

            if(numbers[mid]>x){
                j=mid-1;
            }else{
                i=mid+1;
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
        while(i<j){
            // xcrj 已经遍历过的不能再使用&&numbers已经有序，因此可以这样使用
            if(numbers[i]+numbers[j]==target){
                return new int[]{i,j};
            }

            if(numbers[i]+numbers[j]<target){
                i++;
            }else{
                j--;
            }
        }

        return new int[0];
    }
}