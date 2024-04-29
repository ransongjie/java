package com.xcrj.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets
 * 子集
 * 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的
 * 子集（幂集）。
 * 解集不能包含重复的子集。你可以按任意顺序返回解集。
 */
public class Main4 {
    List<Integer> ls = new ArrayList<>();
    List<List<Integer>> lss = new ArrayList<>();

    //回溯法
    public List<List<Integer>> subsets(int[] nums) {
        f(0, nums);
        return lss;
    }

    private void f(int idx, int[] nums) {
        if (idx == nums.length) {
            lss.add(new ArrayList<>(ls));
            return;
        }

        //选择当前元素，继续往下
        ls.add(nums[idx]);
        f(idx + 1, nums);
        //不选择当前元素，继续往下
        ls.remove(ls.size() - 1);
        f(idx + 1, nums);
    }

    /**
     * 转换为二进制编码
     * 对于每个数都只有选择或不选择两种情况，转成二进制位，0-不选择，1-选择
     * 例如：
     * 3 2 1
     * 0 0 0，1个数都没选
     * 1 0 0，只选择了3
     * <p>
     * 第1位2种情况（选/不选） 第2位2种情况（选/不选） 第3位2种情况（选/不选），共有情况：2*2*2
     * 0~sum的每个数字代表1个子集的二进制编码
     * 0~sum的每个数字的对应bit都代表nums元素的选/不选
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        int sum = 1 << nums.length;
        //子集i第j个bit的值0或1，代表了nums第j个元素的选择与否
        //子集的二进制编码
        for (int i = 0; i < sum; i++) {
            ls.clear();
            //子集元素二进制编码的bit值
            for (int j = 0; j < nums.length; j++) {
                //i的第j位不为0，则选择nums的第j个元素
                if ((i & (1 << j)) != 0) {
                    ls.add(nums[j]);
                }
            }
            lss.add(new ArrayList<>(ls));
        }
        return lss;
    }
}
