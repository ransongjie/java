package com.xcrj.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 */
public class Main3 {
    /**
     * 两个循环 1.数字字符串 2.数字代表的英文字符串
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        //map<数字，字符串>
        Map<Character, String> digitLetter = new HashMap<>() {
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }
        };
        f(digits, digitLetter, 0, new StringBuilder(), ans);
        return ans;
    }

    //回溯
    //1.操作 digit
    private void f(String digits, Map<Character, String> digitLetter, int idx, StringBuilder sb, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        char digit = digits.charAt(idx);
        String letter = digitLetter.get(digit);
        //2.操作 letter
        for (int i = 0; i < letter.length(); i++) {
            //操作第idx个数字的第i个英文字母
            sb.append(letter.charAt(i));
            f(digits, digitLetter, idx + 1, sb, ans);
            sb.deleteCharAt(idx);//idx
        }
    }
}
