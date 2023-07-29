package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 * <p>
 * 分析：类似 剑指 Offer II 082. 含有重复元素集合的组合
 * - 先排序nums，将相等元素放到一起
 * - 插空法，将第j个元素插入第i个空中，相等元素插到一起
 */
public class Solution84 {
    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    boolean[] added;

    public List<List<Integer>> permuteUnique(int[] nums) {
        added=new boolean[nums.length];
        Arrays.sort(nums);
        // 先插入第0个空
        backTrack(nums, 0);

        return rss;
    }

    private void backTrack(int[] nums, int i) {
        // 求得解
        if(i==nums.length){
            rss.add(new ArrayList<>(list));
            return;
        }

        // 轮流把每个元素放到第i个空中
        // xcrj for idx中remove(idx)会报错，这里不报错是因为remove(i)
        for(int j=0;j<nums.length;j++){
            if(added[j]) continue;

            // xcrj 保证相等元素放到一起
            // 已经处理了2个元素以上&&前后两个元素相等&&还没放前1个元素
            if(j>0&&nums[j]==nums[j-1]&&!added[j-1]) continue;

            // 将第j个元素放到第i个空
            list.add(i,nums[j]);
            added[j]=true;
            // 处理下1个空
            backTrack(nums, i+1);
            // 回溯
            list.remove(i);
            added[j]=false;
        }
    }
}
