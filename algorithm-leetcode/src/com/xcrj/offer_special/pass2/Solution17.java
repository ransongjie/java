package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 * - 更复杂的变位词
 * - 两个字符串s和t，s>t
 * - s的子串，因此字符要连续
 * - s的子串包含t中所有字符
 * - 最短的s子串
 * - s和t中含有大写或小写字母，因此不能简单直接使用solution14变位词的解法
 */
public class Solution17 {
    Map<Character, Integer> tmap = new HashMap<>();
    Map<Character, Integer> smap = new HashMap<>();

    /**
     * 参考solution14变位词的解法
     * - tmap统计t中字符出现次数
     * - 双指针夹逼+smap统计s中字符数量
     * - i指向0 j指向-1
     * - 2个while循环夹击
     * while(j<len){
     *   // j向右满足条件
     *   j++;
     *   
     *   while(i<=j&&条件){
     *     // i向右不满足条件
     *     i++;
     *   }
     * }
     * @param s
     * @param t
     * @return 不存在这样的子串 返回""
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        // tmap统计t中字符出现次数
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        // 双指针夹逼
        int i = 0;
        int j = -1;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        while (j < s.length()) {
            // smap中放入存在于tmap中的字符
            j++;
            if (j < s.length()) {
                Character cj = s.charAt(j);
                if (tmap.containsKey(cj)) {
                    smap.put(cj, smap.getOrDefault(cj, 0) + 1);
                }
            }

            while (i <= j && check()) {
                // 更新minLen
                if (j - i + 1 < minLen) {
                    minLen = j - i + 1;
                    start = i;
                }
                // 从smap中移除i指向元素
                Character ci = s.charAt(i);
                if (smap.containsKey(ci)) {
                    smap.put(ci, smap.get(ci) - 1);
                }
                i++;
            }
        }

        return Integer.MAX_VALUE == minLen ? "" : s.substring(start, start + minLen);
    }

    /**
     * 检查 tmap中的每个字符的数量<=smap中每个字符的数量
     * - 遍历tmap，保证tmap中每个字符都存在于smap中
     * - 遍历tmap，保证tmap中每个字符的数量都大于smap中的数量
     * 
     * @return
     */
    public boolean check() {
        for (Entry<Character, Integer> entry : tmap.entrySet()) {
            if (smap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        System.out.println(solution17.minWindow("ADOBECODEBANC", "ABC"));
    }
}