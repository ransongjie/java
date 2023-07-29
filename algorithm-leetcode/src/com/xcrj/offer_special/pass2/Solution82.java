package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 082. 含有重复元素集合的组合
 * 给定一个可能有重复数字的整数数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * <p>
 * 分析
 * - 整数数组candidates中含有重复的数字
 * - candidates中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * 有两个相等元素2，下面两个组合重复了
 * - 12243
 * - 12243
 * 结果
 * - 相等元素放到一起先处理
 */
public class Solution82 {

    List<int[]> numTimes = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    List<List<Integer>> rss = new ArrayList<>();

    /**
     * 回溯法
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序, 让相等数字在一起
        Arrays.sort(candidates);
        // 统计每个数字出现的次数int[]{数字，次数}
        // xcrj for {if continue} 多分支
        for(int c:candidates){
            if(numTimes.isEmpty()){
                numTimes.add(new int[]{c,1});
                // xcrj
                continue;
            }
            // 前后两个数字不同，添加新元素
            if(c!=numTimes.get(numTimes.size()-1)[0]){
                numTimes.add(new int[]{c,1});
                // xcrj
                continue;
            }
            // 前后两个数字相同，数量++
            numTimes.get(numTimes.size()-1)[1]++;
        }
        // 从下标0开始选择
        dfs(0,target);
        return rss;
    }

    private void dfs(int i, int target) {
        // 找到解
        if(target==0){
            rss.add(new ArrayList<>(list));
            return;
        }

        // 剪枝
        if(i==numTimes.size()) return;
        if(target<numTimes.get(i)[0]) return;

        // xcrj 从叶子结点开始做选择
        dfs(i+1,target);
        // 最多能选择几个相等元素
        int k=Math.min(target/numTimes.get(i)[0],numTimes.get(i)[1]);
        // 先选择1~k次相等元素
        for(int j=1;j<=k;j++){
            list.add(numTimes.get(i)[0]);
            dfs(i+1,target-j*numTimes.get(i)[0]);
        }
        // 回溯，不选择1~k次相等元素
        for(int j=1;j<=k;j++){
            list.remove(list.size()-1);
        }
    }
}
