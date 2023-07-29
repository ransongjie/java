package com.xcrj.offer_special.pass3;

/**
 * 剑指 Offer II 005. 不含相同字符的单词的长度的最大乘积
 * - 给定一个字符串数组words，1个元素1个字符串
 * - 计算当两个字符串不包含相同字符时，长度乘积的最大值
 * - 字符串中只包含英语的小写字母
 */
public class Solution5 {

    /**
     * 每一个单词和后面的所有单词对比，蛮力法
     * 单词i从0到len
     * 单词j从i到len
     * 
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        // 不存在满足条件的解 则返回0
        int r = 0;
        for (int i = 0; i < words.length; i++) {
            String wordi = words[i];
            for (int j = i; j < words.length; j++) {
                String wordj = words[j];
                if (!hasSameChar1(wordi, wordj)) {
                    r = Math.max(r, wordi.length() * wordj.length());
                }
            }
        }

        return r;
    }

    /**
     * 单词i中是否含有单词j的字符，遍历单词j的每个字符，判断字符是否在单词i中
     * 
     * @param wordi
     * @param wordj
     * @return
     */
    public boolean hasSameChar1(String wordi, String wordj) {
        for (char cj : wordj.toCharArray()) {
            // 存在字符返回 !-1
            if (-1 != wordi.indexOf(cj))
                return true;
        }
        return false;
    }

    /**
     * 利用散列表存储单词中的字符
     * 散列函数 c-'a'=idx
     * 
     * @param wordi
     * @param wordj
     * @return
     */
    public boolean hasSameChar2(String wordi, String wordj) {
        // 散列表
        int[] cs = new int[26];
        // 存储wordi
        for (char c : wordi.toCharArray()) {
            int idx = c - 'a';
            cs[idx] = 1;
        }

        // 判断wordj的字符是否已经存储到cs散列表中
        for (char c : wordj.toCharArray()) {
            int idx = c - 'a';
            if (1 == cs[idx])
                return true;
        }

        return false;
    }

    /**
     * 位散列表，两个整数分别存储两个字符串，整数&运算==0 判断是否存在相同字符
     * 散列函数 bm|=1<<(c-'a')
     * 
     * @param wordi
     * @param wordj
     * @return
     */
    public boolean hasSameChar3(String wordi, String wordj) {
        // xcrj |运算初始值为0
        int bm1 = 0;
        int bm2 = 0;
        for (char c : wordi.toCharArray()) {
            bm1 |= 1 << (c - 'a');
        }

        for (char c : wordj.toCharArray()) {
            bm2 |= 1 << (c - 'a');
        }

        return 0 == (bm1 & bm2) ? false : true;
    }
}
