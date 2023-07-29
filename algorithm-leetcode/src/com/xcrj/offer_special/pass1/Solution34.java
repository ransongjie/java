package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 034. 外星语言是否排序
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 * 说明：
 * order是字母表
 * words是几个单词组成的一段话
 * - 若 words中单词相同位置字符满足 字典order顺序
 * - 空白字符位于字典order的最后
 */
public class Solution34 {

    /**
     * 使用idxs[]记录字符串中字符的顺序，下标代表字符，值记录该字符的索引
     */
    public boolean isAlienSorted(String[] words, String order) {
        // 记录字典order中每个字符在order中的索引位置
        int[] idxs = new int[26];
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            // 单词在order中的什么位置
            idxs[c - 'a'] = i;
        }
        /**
         * 依次比较两个单词相同位置字符在order中的索引顺序
         * - 如果 前1个单词的字符索引>后1个单词的字符索引 直接返回false
         * - 如果 前1个单词的字符索引<后1个单词的字符索引 直接比较下两个单词
         * - 如果 前1个单词的字符索引=后1个单词的字符索引 比较单词的长度
         */
        for (int i = 1; i < words.length; i++) {
            // !!! for内部判断>和<, for外部使用equalFlag记录是否需要判断==
            boolean equalFlag = false;
            for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
                int idxPrev = idxs[words[i - 1].charAt(j) - 'a'];
                int idxCurt = idxs[words[i].charAt(j) - 'a'];
                // 前1个字符的字典序>后1个字符的字典序 直接返回false
                if (idxPrev > idxCurt) {
                    return false;
                }
                // 前1个字符的字典序<后1个字符的字典序 直接判断下两个单词
                if (idxPrev < idxCurt) {
                    equalFlag = true;
                    break;
                }
            }
            // 前1个字符的字典序=后1个字符的字典序 直接判断两个单词的长度, 即 前1个单词的字符和后1个单词的空白字符对比 空白字符在order的最后位置 顺序最小
            if (!equalFlag) {
                if (words[i - 1].length() > words[i].length()) return false;
            }
        }

        return true;
    }
}
