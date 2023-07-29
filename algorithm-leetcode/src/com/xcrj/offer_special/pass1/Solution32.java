package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 032. 有效的变位词
 * 与剑指 Offer II 014. 字符串中的变位词，s1字符串的某个排列是s2的子串 类似
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 * s and t 仅包含小写字母
 * 必须是变位词，s和t的字符顺序都一致时，是相同字符串，不是变位词
 */
public class Solution32 {

    public boolean isAnagram1(String s, String t) {
        // 处理相同字符
        if (s.equals(t)) return false;

        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int[] cnts = new int[26];
        for (char c : ss) {
            cnts[c - 'a']++;
        }

        for (char c : ts) {
            cnts[c - 'a']--;
        }

        for (int a : cnts) {
            if (a != 0) return false;
        }
        return true;
    }

    /**
     * 只要求字母数量一致，统计每个字母的数量即可
     * 使用数组散列表
     * cnt[26] 散列函数 c-'a'
     */
    public boolean isAnagram2(String s, String t) {
        // 处理长度不一致的情况
        if (s.length() != t.length()) return false;
        // 处理相同字符
        if (s.equals(t)) return false;

        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int[] cnts = new int[26];
        for (char c : ss) {
            cnts[c - 'a']++;
        }

        for (char c : ts) {
            cnts[c - 'a']--;

            if (cnts[c - 'a'] < 0) return false;
        }
        return true;
    }

    /**
     * 进阶：输入字符串包含 unicode 字符
     * 使用map<Character, Integer>
     */
    public boolean isAnagram3(String s, String t) {
        // 处理长度不一致的情况
        if (s.length() != t.length()) return false;
        // 处理相同字符
        if (s.equals(t)) return false;

        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : ss) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ts) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution32 solution32 = new Solution32();
        System.out.println(solution32.isAnagram1("a", "a"));
    }
}
