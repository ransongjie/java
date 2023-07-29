package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 005. 单词长度的最大乘积
 * 给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 */
public class Solution5 {

    /**
     * 蛮力法，遍历
     */
    public int maxProduct1(String[] words) {
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            String wordi = words[i];
            // j=i: word0和word1比较过了，word1和word0不用再比较
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
     * wordj.indexOf(c)
     * 有1个相同字符就返回true
     */
    public boolean hasSameChar1(String wordi, String wordj) {
        for (char c : wordi.toCharArray()) {
            // 只有有1个相同字符则返回true
            if (-1 != wordj.indexOf(c)) return true;
        }
        return false;
    }

    /**
     * 字母 数组散列表统计
     */
    public int maxProduct2(String[] words) {
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            String wordi = words[i];
            // j=i: word0和word1比较过了，word1和word0不用再比较
            for (int j = i; j < words.length; j++) {
                String wordj = words[j];
                if (!hasSameChar2(wordi, wordj)) {
                    maxLen = Math.max(maxLen, wordi.length() * wordj.length());
                }
            }
        }

        return maxLen;
    }

    /**
     * 数组散列表：将不同字母散列到闭散列表的不同位置
     * 散列函数：idx=c-'a'
     */
    public boolean hasSameChar2(String wordi, String wordj) {
        // 26个字母，26个空间的散列表
        int[] counts = new int[26];
        // 将wordi中每个字母放入counts数组中的不同位置
        for (char c : wordi.toCharArray()) {
            int idx = c - 'a';
            counts[idx] = 1;
        }
        // 将wordj中每个字母放入counts数组中的不同位置
        for (char c : wordj.toCharArray()) {
            int idx = c - 'a';
            // idx散列位置处已经有值了，证明存在相同字母
            if (1 == counts[idx]) {
                return true;
            }
        }

        return false;
    }


    /**
     * 字母 位散列表统计
     */
    public int maxProduct3(String[] words) {
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            String wordi = words[i];
            // j=i: word0和word1比较过了，word1和word0不用再比较
            for (int j = i; j < words.length; j++) {
                String wordj = words[j];
                if (!hasSameChar3(wordi, wordj)) {
                    maxLen = Math.max(maxLen, wordi.length() * wordj.length());
                }
            }
        }

        return maxLen;
    }

    /**
     * 位散列统计
     * 散列函数：bitMask=1<<c-'a'
     */
    public boolean hasSameChar3(String wordi, String wordj) {
        // 构建32bit闭散列表
        int bitMask1 = 0;
        int bitMask2 = 0;
        for (char c : wordi.toCharArray()) bitMask1 |= 1 << c - 'a';
        for (char c : wordj.toCharArray()) bitMask2 |= 1 << c - 'a';
        // 如果没有相同的字符则&结果为0;
        return 0 == (bitMask1 & bitMask2) ? false : true;
    }

    /**
     * 取得每个单词位散列表示，存储到bitMasks[]中
     */
    public int maxProduct4(String[] words) {
        // 存储每个单词的位散列表示
        int[] bitMasks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bitMask = 0;
            for (char c : word.toCharArray()) bitMask |= 1 << c - 'a';
            bitMasks[i] = bitMask;
        }

        // 寻找没有相同位的单词
        int maxLen = 0;
        for (int i = 0; i < bitMasks.length; i++) {
            int bitMaski = bitMasks[i];
            String wordi = words[i];
            for (int j = i; j < bitMasks.length; j++) {
                int bitMaskj = bitMasks[j];
                String wordj = words[j];
                if (0 == (bitMaski & bitMaskj)) {
                    maxLen = Math.max(maxLen, wordi.length() * wordj.length());
                }
            }
        }

        return maxLen;
    }

    /**
     * hashMap统计优化maxProduct4
     * Map<bitMask,Len>
     */
    public int maxProduct5(String[] words) {
        // 存储每个单词的位散列表示
        Map<Integer, Integer> maskLenMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bitMask = 0;
            for (char c : word.toCharArray()) bitMask |= 1 << c - 'a';
            // ab aabb单词的bitMask一致，取aabb的最大长度
            maskLenMap.put(bitMask, Math.max(maskLenMap.getOrDefault(bitMask, 0), word.length()));
        }

        // 找出没有相同字符的单词
        int maxLen = 0;
        for (int ki : maskLenMap.keySet()) {
            for (int kj : maskLenMap.keySet()) {
                // 不含有相同字符
                if (0 == (ki & kj)) {
                    maxLen = Math.max(maxLen, maskLenMap.get(ki) * maskLenMap.get(kj));
                }
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.maxProduct5(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
    }
}
