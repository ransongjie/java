package com.xcrj.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 含有重复元素，不允许重复选择相同元素，解集不能包含重复的组合
 * 组合：相同的数字放到不同的位置认为是同一个组合
 * https://leetcode.cn/problems/4sjJUc/
 */
public class Main1a {
    List<List<Integer>> lss = new ArrayList<>();
    List<Integer> ls = new ArrayList<>();
    List<int[]> numTimes = new ArrayList<>();//number:times

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        //统计每个候选数字的出现次数
        for (int num :
                candidates) {
            if (numTimes.isEmpty()) {
                numTimes.add(new int[]{num, 1});
                continue;
            }
            //遍历元素值 != numTimes结尾元素值
            int lastIdx = numTimes.size() - 1;
            if (num != numTimes.get(lastIdx)[0]) {
                numTimes.add(new int[]{num, 1});
                continue;
            }
            //遍历元素值 == numTimes结尾元素值
            numTimes.get(lastIdx)[1]++;//数组是引用变量
        }
        f(0, target);
        return lss;
    }

    //回溯
    private void f(int idx, int rest) {
        if (rest == 0) {
            lss.add(new ArrayList<>(ls));
            return;
        }
        if (idx == numTimes.size()) return;
        if (rest < numTimes.get(idx)[0]) return;
        //不选择当前元素
        f(idx + 1, rest);
        //选择当前元素，重复的数字一次性处理完毕（相当于把重复的数字在解集中放到一起，这样解集就不会包含重复的组合）
        int k = Math.min(rest / numTimes.get(idx)[0], numTimes.get(idx)[1]);
        for (int i = 1; i <= k; i++) {
            ls.add(numTimes.get(idx)[0]);
            f(idx + 1, rest - i * numTimes.get(idx)[0]);
        }
        //回退
        for (int i = 0; i < k; i++) {
            ls.remove(ls.size() - 1);
        }
    }
}
