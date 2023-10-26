package com.xcrj.dp.knapsack_complete;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/word-break/submissions/
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被拆分为一个或多个在字典中出现的单词。
 * <p>
 * bagSize: s.len
 * 物品重量：wordDict中的单词
 * 顺序：求排列
 */
public class Main7 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int bagSize = s.length();
        // dp[子串]=字典能否组成
        boolean[] dp = new boolean[bagSize + 1];
        dp[0] = true;
        // 和顺序有关，单词a在前和单词b在前组成的是不同的子串
        for (int i = 1; i < bagSize + 1; i++) {// 非空字符串 i=1
            for (int j = 0; j < i && !dp[i]; j++) {// !dp[i] 已经为true了不用再求
                dp[i] = dp[j] && set.contains(s.substring(j, i));
            }
        }
        return dp[bagSize];
    }
}
