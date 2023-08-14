package com.xcrj.interview150.arr_str;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string
 * 反转字符串中的单词
 * O(n)
 */
public class Solution11 {
    public String reverseWords(String s) {
        s = s.trim();
        // 正则匹配 连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
