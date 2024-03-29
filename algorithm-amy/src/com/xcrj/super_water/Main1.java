package com.xcrj.super_water;

/**
 * https://leetcode.cn/problems/majority-element/
 * 多数元素是指在数组中出现次数 大于 ⌊n/2⌋ 的元素，水王数
 * 参考：https://blog.csdn.net/qq_45173404/article/details/117809364
 */
public class Main1 {
    /**
     * 思想：先找候选者，再确定胜利者
     * 原理：从数组第一个元素开始，依次删掉两个不同的数，最后如果有水王数的话，它会剩下来
     * （反过来不成立，也就是剩下来的数不一定是水王数）
     * 步骤：
     * 1.先获取可能水王数
     * 2.再判断真正水王数
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        //获取候选水王数
        int cand = nums[0], cnt = 0;
        for (int i = 0; i < nums.length; i++) {
//            if (cand == nums[i]) cnt++;
//            else cnt--;
//            if (cnt <= 0) {
//                cand = nums[i];
//                cnt = 0;
//            }
            //上面代码无法通过case [3,2,3]
            if (cnt == 0) {
                cand = nums[i];
                cnt++;
            } else if (cand == nums[i]) cnt++;
            else cnt--;
            //修改正确
//            if (cand == nums[i]) cnt++;
//            else cnt--;
//            if (cnt <= 0) {
//                if(i+1==nums.length) break;
//                cand = nums[i+1];//跳过和候选者比较过的元素
//                cnt = 0;
//            }
        }

        //是否存在水王数
        if (cnt <= 0) return -1;

        //是否真正水王数
        cnt = 0;
        for (int num :
                nums) {
            if (num == cand) cnt++;
        }
        if (cnt > nums.length / 2) return cand;
        else return -1;
    }
}
