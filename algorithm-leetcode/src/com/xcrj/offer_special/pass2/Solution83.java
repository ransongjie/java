package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 */
public class Solution83 {

    List<Integer> list = new ArrayList<>();
    List<List<Integer>> rss = new ArrayList<>();

    /**
     * 回溯法
     * 把那个元素放到剩余序列的开头
     */
    public List<List<Integer>> permute1(int[] nums) {
        // 构建初始序列
        for(int num:nums)list.add(num);
        // 升序序列的开头为0
        backTrack(list.size(), 0);

        return rss;
    }

    private void backTrack(int len, int first) {
        // 找到解
        if(len==first){
            rss.add(new ArrayList<>(list));
            return;
        }

        // 把升序序列中的每个元素逐个放到剩余序列的开头first
        for(int i=first;i<len;i++){
            // 第i个元素放到剩余序列的开头first
            Collections.swap(list, first, i);
            // 处理下1个开头
            backTrack(len, first+1);
            // 回溯
            Collections.swap(list, first, i);
        }
    }
}
