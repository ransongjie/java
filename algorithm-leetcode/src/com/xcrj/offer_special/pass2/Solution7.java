package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 007. 数组中和为0的三个数
 * - 数组元素是无序的
 * - 找出所有和为0的三元组
 * - 两个三元组中对应位置元素不能相同
 */
public class Solution7 {

    /**
     * 问题转换
     * - 本问题 转化为 剑指 Offer II 006. 排序数组中两个数字之和，从已升序排序数组中寻找两个数，他们的和为target
     * - 先升序排序
     * - 再使用"剑指 Offer II 006"的方法
     * 
     * 过程
     * - 确定target：target=0-nums[i]
     * - 双指针反向移动寻找target
     * - set存储所有和为0的三元组
     * - set存储唯一三元组
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int k = 0; k < nums.length; k++) {
            int target = 0 - nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum= nums[i] + nums[j];
                if (target == sum) {
                    // xcrj Arrays.asList(T... a) 可变参数
                    // xcrj ArrayList的equal()根据 对象引用相等||数组中对应位置元素值相等
                    set.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    // 继续寻找
                    i++;
                    j--;
                } else if (target > sum) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        // xcrj ArrayList(Collection<? extends E> c) 将其它集合类型存储到List中
        return new ArrayList<>(set);
    }

    /**
     * 过程
     * - 先问题转换
     * - 再使用双指针反向移动求解
     * - 使用List<List<Integer>>结构存储结果
     * - 遍历过程中相连的相等元素只使用最后1个，避免两个三元组对应位置元素相等
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int k = 0; k < nums.length; k++) {
            // xcrj 相连相等元素只使用最后1个, 
            // [k1,i,j] [k2,i,j] k1=k2 三元组重复
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }

            int target = 0 - nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum=nums[i] + nums[j];
                if (target == sum) {
                    list.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    // xcrj 相连相等元素只使用第1个, 
                    // [k,i1,j] [k,i2,j] i1=i2 三元组重复
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]);
                }else if(target>sum){
                    i++;
                }else{
                    j--;
                }
            }
        }
        
        return list;
    }
}
