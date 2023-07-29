package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 063. 替换单词
 * 在英语中，有一个叫做词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为继承词(successor)。例如，词根an，跟随着单词other(其他)，可以形成新的单词another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 需要输出替换之后的句子。
 * <p>
 * 继承词用词根替代
 * 继承词有多个词根，用最短的词根替代
 */
public class Solution63 {

    /**
     * 蛮力法，Set<prefixString>单词子串是否在set中
     */
    public String replaceWords1(List<String> dictionary, String sentence) {
        // 把字典中所有字符串放入Set中
        Set<String> set = new HashSet<>();
        for (String str : dictionary) {
            set.add(str);
        }
        // 遍历每个字符串的子串是否包含在set中
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                // 最小的词根，逐步扩大的子串，先找到最小的子串
                if (set.contains(strs[i].substring(0, j + 1))) {
                    // 子串截取 前包后不包
                    strs[i] = strs[i].substring(0, j + 1);
                    // 找到1个词根就OK
                    break;
                }
            }
        }

        // ！字符串join
        return String.join(" ", strs);
    }

    /**
     * 字典树 Map<Character, Trie> children，结尾Map<'#',new Trie()>
     */
    public String replaceWords2(List<String> dictionary, String sentence) {
        // 构建字典树
        Trie trie = new Trie();
        for (String str : dictionary) {
            Trie node = trie;
            for (char c : str.toCharArray()) {
                node.children.putIfAbsent(c, new Trie());
                node = node.children.get(c);
            }
            // 结束用特殊字符
            node.children.put('#', new Trie());
        }

        // 使用前缀字符串替换原字符串
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = availableMinPrefix(trie, words[i]);
        }

        return String.join(" ", words);
    }

    /**
     * 最短前缀字符串，字典树逐个字符查询字典 到达#结束字符就返回
     *
     * @return 最短前缀字符串或原字符串
     */
    private String availableMinPrefix(Trie trie, String word) {
        StringBuilder sb = new StringBuilder();
        Trie node = trie;
        // ！分治法，先处理最特殊的情况
        for (char c : word.toCharArray()) {
            // 到达结束字符，用前缀替换
            if (node.children.containsKey('#')) {
                return sb.toString();
            }

            // 到达非前缀字符，不用前缀进行替换
            if (!node.children.containsKey(c)) {
                return word;
            }

            // 到达前缀字符，添加到sb中
            sb.append(c);
            node = node.children.get(c);
        }

        return sb.toString();
    }

    // 字典树的一个结点
    class Trie {
        Map<Character, Trie> children;

        public Trie() {
            this.children = new HashMap<>();
        }
    }
}
