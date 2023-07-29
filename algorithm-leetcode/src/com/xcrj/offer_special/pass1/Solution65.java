package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 * - words.length == indices.length
 * - 助记字符串 s 以 '#' 字符结尾
 * - 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * - 给定一个单词数组words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 * <p>
 * 注意：
 * - 1 <= words.length <= 2000
 * - 1 <= words[i].length <= 7
 * - words[i] 仅由小写字母组成
 * <p>
 * 分析：
 * - 1 <= words[i].length <= 7 最多有7个后缀
 * - 单词数组的有效编码，去掉后缀单词
 * - A单词是B单词的后缀则去掉A单词
 */
public class Solution65 {

    /**
     * 蛮力法去掉存储与Set中的可能的每个单词的后缀子串
     */
    public int minimumLengthEncoding1(String[] words) {
        // 将所有单词存储到set中
        // ！HashSet的构造函数
        Set<String> set = new HashSet<>(Arrays.asList(words));

        // 获取每个单词的每种可能后缀，并在set中去除
        for (String word : words) {
            for (int j = 1; j < word.length(); j++) {
                // 移除单词可能的后缀
                set.remove(word.substring(j));
            }
        }

        // 结果
        int r = 0;
        for (String str : set) {
            // 1是#，有效单词的后面含有#
            r += str.length() + 1;
        }
        return r;
    }

    /**
     * 反序单词插入字典树，统计字典树到叶子结点的长度+1(#符号)
     */
    public int minimumLengthEncoding2(String[] words) {
        // 存储map<结点,单词索引>
        Map<Tire, Integer> map = new HashMap<>(words.length);
        // 构建字典树
        Tire root = new Tire();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 后缀树
            Tire node = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                node = node.getNode(word.charAt(j));
            }
            // 存储map<结点,单词索引>，结点包括叶子结点
            map.put(node, i);
        }

        // 结果
        int r = 0;
        for (Map.Entry<Tire, Integer> entry : map.entrySet()) {
            // 是否是叶子结点
            if (!entry.getKey().added) {
                r += words[entry.getValue()].length() + 1;
            }
        }
        return r;
    }

    class Tire {
        // 子结点
        Tire[] children;
        // 是否新增过结点，叶子结点不会新增结点
        boolean added;

        public Tire() {
            // 26 26个小写字母
            this.children = new Tire[26];
            this.added = false;
        }

        /**
         * 获取字母对应的结点
         *
         * @param c 字母
         */
        public Tire getNode(char c) {
            int idx = c - 'a';
            // 不含有这个字母，则创建新结点
            if (null == this.children[idx]) {
                this.children[idx] = new Tire();
                this.added = true;
            }
            return this.children[idx];
        }
    }
}
