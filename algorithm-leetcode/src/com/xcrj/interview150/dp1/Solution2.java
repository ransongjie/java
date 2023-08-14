package com.xcrj.interview150.dp1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/word-break
 * 单词拆分
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 */
public class Solution2 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (String str :
                wordDict) {
            set.add(str);
        }
        //dp[i]，长度为i的字符串是否能够由wordDict组成
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;//空串
        for (int i = 1; i <= n; i++) {//<=n
            //dp[0~i]=dp[0~j]&&check(j~i), 0~i的子串 能否由wordDict组成=0~j的子串能否由 wordDict组成 && j~i子串是否在wordDict中
            //两两递推
            //长度为i的子串 长度为j的子串 j~i的子串
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
