package com.xcrj.interview150.slide_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 无重复字符的最长子串
 */
public class Solution17 {
    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        solution17.lengthOfLongestSubstring("abba");
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        int res = 0, l = 0, r = 0, cnt = 0;
        //Map<Char,次数>
        Map<Character, Integer> charCnt = new HashMap<>();
        //先破坏条件
        while (r < n) {
            //有重复字符
            if (charCnt.containsKey(s.charAt(r))) {
                cnt++;
            }
            charCnt.put(s.charAt(r), charCnt.getOrDefault(s.charAt(r), 0) + 1);
            //再满足条件
            while (cnt > 0) {
                //去除重复字符
                if (charCnt.get(s.charAt(l)) > 1) {
                    cnt--;
                    charCnt.put(s.charAt(l), charCnt.get(s.charAt(l)) - 1);
                } else {
                    charCnt.remove(s.charAt(l));
                }
                l++;
            }
            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }
}
