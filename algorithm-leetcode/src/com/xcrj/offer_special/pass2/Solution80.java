package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 080. 含有 k 个元素的组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Solution80 {
    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 回溯法
     * 剪枝函数
     * - 约束函数：约束条件
     * - 限界函数：得不到解的分钟
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine1(int n, int k) {
        // 开始选择第1个元素
        dfs(n,k,1);
        return rss;
    }

    /**
     * @param n   1~n的数
     * @param k   从1~n选择k个数
     * @param cur 当前选择的数
     */
    private void dfs(int n, int k, int cur) {
        // 限界函数，已选择的元素数量+未选择的元素总量<k
        if(list.size()+n-cur+1<k) return;
        
        if(list.size()==k){
            rss.add(new ArrayList<>(list));
            return;
        }

        // 约束函数
        if(cur>n) return;
        
        list.add(cur);
        dfs(n,k,cur+1);
        list.remove(list.size()-1);
        dfs(n,k,cur+1);
    }
}
