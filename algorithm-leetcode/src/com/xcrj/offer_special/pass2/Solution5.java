package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 005. 不含相同字符的单词的长度的最大乘积
 * - 给定一个字符串数组words，1个元素1个字符串
 * - 计算当两个字符串不包含相同字符时，长度乘积的最大值
 * - 字符串中只包含英语的小写字母
 */
public class Solution5 {
    /**
     * 蛮力法
     * - 单词i从0到len
     * - 单词j从i到len
     */
    public int maxProduct1(String[] words) {
        // xcrj 0，所以单词都包含相同字符，返回0
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            String wordi = words[i];
            for (int j = i; j < words.length; j++) {
                String wordj = words[j];
                if (!hasSameChar1(wordi, wordj)) {
                    maxLen = Math.max(maxLen, wordi.length() * wordj.length());
                }
            }
        }
        return maxLen;
    }

    /**
     * 单词j中是否有单词i的字母
     * - 遍历单词i的每个字母
     * - 检查字母是否在单词j中
     * 
     * @return true表示含有相同字母
     */
    public boolean hasSameChar1(String wordi, String wordj) {
        for (char c : wordj.toCharArray()) {
            if (-1 != wordi.indexOf(c)) {
                return true;
            }
        }
        return false;
    }


    /**
     * xcrj 散列表判两单词含相同字母
     * - 26个字母数组散列表
     * - 散列函数：idx=c-'a'
     * - 单词i存储到散列表中
     * - 单词j字母是否已经在散列表中
     */
    public boolean hasSameChar2(String wordi, String wordj) {
        int[] counts = new int[26];
        for (char c : wordi.toCharArray()) {
            int idx = c - 'a';
            counts[idx] = 1;
        }

        for (char c : wordj.toCharArray()) {
            int idx = c - 'a';
            if (1 == counts[idx]) {
                return true;
            }
        }

        return false;
    }

    /**
     * 位散列表
     * - 散列函数：bitMask |= 1 << c - 'a'
     * - 单词i放入bitMask1中
     * - 单词j放入bitMask2中
     * - 有一位都为1则存在相同字母
     */
    public boolean hasSameChar3(String wordi, String wordj) {
        int bitMask1 = 0;
        int bitMask2 = 0;
        for (char c : wordi.toCharArray()) {
            bitMask1 |= 1 << (c - 'a');
        }

        for (char c : wordj.toCharArray()) {
            bitMask2 |= 1 << (c - 'a');
        }

        // xcrj 0，判断少的情况，剩下的为其它情况
        return 0 == (bitMask1 & bitMask2) ? false : true;
    }

    
}
