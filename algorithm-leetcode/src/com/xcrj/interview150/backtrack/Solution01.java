package com.xcrj.interview150.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 */
public class Solution01 {
    public List<String> letterCombinations(String digits) {
        //处理特殊情况
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        //map<数字，字符串>
        Map<Character, String> digitStr = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        //处理digits中第idx个数字的每个字母
        backtrack(ans, digitStr, digits, 0, new StringBuilder());
        //返回结果
        return ans;
    }

    /**
     * digits中第idx个数字的每个字母
     * 先处理digits中第idx个数字，再处理digits中第idx+1个数字
     *
     * @param ans      结果们
     * @param digitStr
     * @param digits
     * @param idx      digits[idx]
     * @param an       一个结果
     */
    private void backtrack(List<String> ans, Map<Character, String> digitStr, String digits, int idx, StringBuilder an) {
        if (idx == digits.length()) {
            ans.add(an.toString());
            return;
        }
        char digit = digits.charAt(idx);
        String str = digitStr.get(digit);
        for (int i = 0; i < str.length(); i++) {
            an.append(str.charAt(i));
            backtrack(ans, digitStr, digits, idx + 1, an);
            an.deleteCharAt(idx);
        }
    }
}
