package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 * t中的所有字符都要包含，t中若有重复的字符也要包含
 * 给定两个字符串 s 和t 。返回 s 中包含t的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * 注意 s和t中含有大写或小写字母，因此不能简单直接使用solution15变位词的解法
 */
public class Solution17 {
    // Map<字符,出现次数>
    private Map<Character, Integer> tmap = new HashMap<>();
    //
    private Map<Character, Integer> smap = new HashMap<>();

    /**
     * 双指针
     * r不停的右移，直到l~r的字符都包含在t中
     * t的所有字符仍然被包含，l左移
     */
    public String minWindow1(String s, String t) {
        // 特殊情况
        if (s.length() < t.length()) {
            return "";
        }

        // 统计t中字符
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }

        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int l = 0;
        int r = -1;
        // ！！！记住这个滑动窗口的双wihle模板代码
        while (r < s.length()) {
            // 右移r：r不停的右移，直到l~r的字符都包含在t中
            r++;
            if (r < s.length() && tmap.containsKey(s.charAt(r))) {
                // 统计t包含 s字符串的l~r字符
                smap.put(s.charAt(r), smap.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 右移l：检查l~r的s子串中的每个字符的数量>=t中每个字符的数量，尝试右移l
            while (l <= r && check1()) {
                // 右移l过程中判断最小长度
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                // 统计，剔除l指向的s中的字符
                if (smap.containsKey(s.charAt(l))) {
                    smap.put(s.charAt(l), smap.get(s.charAt(l)) - 1);
                }
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * l~r的s子串中的每个字符的数量>=t中每个字符的数量
     * 检查t字符串中的所有字符是否都被包含在了 s字符串中的l~r中
     * 已知 t字符串的统计散列表 tmap
     * 已知 s字符串l~r的统计散列表 smap
     */
    public boolean check1() {
        for (Map.Entry<Character, Integer> tentry : tmap.entrySet()) {
            // l~r在smap中统计的字符数量 一定要>= tmap统计的字符数量，如果小于则不满足 s(l~r)要包含t中所有字符的条件，如果>则存在右移的机会
            if (smap.getOrDefault(tentry.getKey(), 0) < tentry.getValue()) {
                return false;
            }
        }
        // l~r的s子串中的每个字符的数量>=t中每个字符的数量
        return true;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        System.out.println(solution17.minWindow1("a", "b"));
    }
}
