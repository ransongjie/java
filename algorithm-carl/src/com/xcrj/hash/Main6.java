package com.xcrj.hash;

/**
 * https://leetcode.cn/problems/ransom-note/
 * 赎金信
 * 赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * 杂志字符串中的每个字符只能在赎金信字符串中使用一次
 * 假设两个字符串均只含有小写字母。
 */
public class Main6 {
    /**
     * ransomNote 的所有字符都在 magazine 中，数量也更多
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnts = new int[26];
        for (char c :
                magazine.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char b :
                ransomNote.toCharArray()) {
            cnts[b - 'a']--;
        }
        for (int n :
                cnts) {
            if (n < 0) return false;
        }
        return true;
    }
}
