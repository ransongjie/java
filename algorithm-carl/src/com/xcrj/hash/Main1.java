package com.xcrj.hash;

/**
 * https://leetcode.cn/problems/valid-anagram/
 * 有效的字母异位词
 */
public class Main1 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] cnts = new int[26];
        for (char c :
                s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c :
                t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int a :
                cnts) {
            if (a != 0) return false;
        }
        return true;
    }
}
