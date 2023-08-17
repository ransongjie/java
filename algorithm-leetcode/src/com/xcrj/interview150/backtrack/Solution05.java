package com.xcrj.interview150.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class Solution05 {
    /**
     * 有效括号
     * - 若左括号数量<limit，可以继续添加左括号
     * - 若右括号数量<左括号数量，可以继续添加右括号
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        // n=左括号数量=右括号数量。0初始在brackets[0]添加括号，左括号数量初始为0，右括号数量初始为0
        backTrack(n, ans, new StringBuilder(), 0, 0);
        return ans;
    }

    /**
     * 左括号数量不达标 添加左括号
     * 右括号数量不达标 添加右括号
     *
     * @param limit 限制长度
     * @param open  左括号数量
     * @param close 右括号数量
     */
    private void backTrack(int limit, List<String> ans, StringBuilder an, int open, int close) {
        // 左右括号添加完毕
        if (an.length() == limit * 2) {
            ans.add(an.toString());
            return;
        }

        // 若左括号数量<limit
        if (open < limit) {
            // 添加左括号
            an.append('(');
            // 再在brackets[i+1]添加括号
            backTrack(limit, ans, an, open + 1, close);
            // 回溯，删除添加的左括号
            an.deleteCharAt(an.length() - 1);
        }

        // 若右括号数量<左括号数量
        if (close < open) {
            // 添加右括号
            an.append(')');
            // 再在brackets[i+1]添加括号
            backTrack(limit, ans, an, open, close + 1);
            // 回溯，删除添加的右括号
            an.deleteCharAt(an.length() - 1);
        }
    }
}
