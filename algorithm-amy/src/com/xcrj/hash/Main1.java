package com.xcrj.hash;

/**
 * https://leetcode.cn/problems/valid-anagram/
 * 有效的字母异位词
 * 都是小写字母
 */
public class Main1 {
    public boolean isAnagram(String s, String t) {
        //长度
        if (s.length() != t.length()) return false;
        //计数+1
        int[] cnts = new int[26];//26
        for (char c :
                s.toCharArray()) {
            cnts[c - 'a']++;
        }
        //计数-1
        for (char c :
                t.toCharArray()) {
            cnts[c - 'a']--;
        }
        //是否为0
        for (int a :
                cnts) {
            if (a != 0) return false;
        }
        return true;
    }
}
