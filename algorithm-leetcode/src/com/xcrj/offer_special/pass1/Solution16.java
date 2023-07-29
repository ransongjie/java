package com.xcrj.offer_special.pass1;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 016. 不含重复字符的最长子字符串
 * 子串中不能有重复字符
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 */
public class Solution16 {

    /**
     * 双指针+set散列表
     * set<Character> set.contains()是否包含重复字符
     * 不包含重复字符 right继续右移
     * 包含重复字符 left右移
     */
    public int lengthOfLongestSubstring1(String s) {
        Set<Character> set = new HashSet<>(s.length());
        int i = 0;
        int maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            // 不包含重复字符 右指针一直右移
            while (i < s.length() && !set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                i++;
            }
            // 包含重复字符 左指针一直右移(尝试把重复的那个字符移除掉)
            set.remove(s.charAt(j));
            maxLen = Math.max(maxLen, i - j);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution16 solution16 = new Solution16();
        System.out.println(solution16.lengthOfLongestSubstring1("abc"));
    }
}
