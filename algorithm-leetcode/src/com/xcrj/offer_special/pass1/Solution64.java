package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 064. 神奇的字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 * 实现 MagicDictionary 类：
 * - MagicDictionary() 初始化对象
 * - void buildDict(String[]dictionary) 使用字符串数组dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * - bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 */
public class Solution64 {
    /**
     * 蛮力法
     * 与每个字符串做对比，对比两个字符串的差异字符数量
     */
    class MagicDictionary1 {
        private String[] strs;

        public MagicDictionary1() {
        }

        public void buildDict(String[] dictionary) {
            this.strs = dictionary;
        }

        public boolean search(String searchWord) {
            // 与每个字符串做对比
            for (String str : this.strs) {
                // 两字符串长度不同比较下一个字符串
                if (str.length() != searchWord.length()) continue;
                // 对比两个字符串的差异字符数量
                int diff = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) != searchWord.charAt(i)) diff++;
                    if (diff > 1) break;
                }
                if (diff == 1) return true;
            }
            return false;
        }
    }

    /**
     * 字典树，前缀树
     */
    class MagicDictionary2 {
        // 根节点
        private Trie root;

        /**
         * 初始化字典树根节点
         */
        public MagicDictionary2() {
            this.root = new Trie();
        }

        /**
         * 构建字典树
         */
        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                // 不同字符串使用同一个根节点
                Trie note = this.root;
                for (char c : str.toCharArray()) {
                    int idx = c - 'a';
                    if (note.children[idx] == null) {
                        note.children[idx] = new Trie();
                    }
                    note = note.children[idx];
                }
                note.finished = true;
            }
            return;
        }

        public boolean search(String searchWord) {
            return dfs(searchWord, this.root, 0, false);
        }

        /**
         * 深度优先搜索查询
         *
         * @param searchWord
         * @param node       下一个结点
         * @param pos        位置
         * @param replaced   true被替换过1个字母
         */
        private boolean dfs(String searchWord, Trie node, int pos, boolean replaced) {
            // pos到达searchWord尾部，searchWord被访问完
            if (pos == searchWord.length()) {
                // 被替换过1次&&字典树字符串被访问完
                return replaced && node.finished;
            }
            // pos未达到searchWord尾部&&字典树中下一个字符存在 则继续向下访问
            int idx = searchWord.charAt(pos) - 'a';
            if (node.children[idx] != null) {
                if (dfs(searchWord, node.children[idx], pos + 1, replaced)) {
                    return true;
                }
            }
            // pos未达到searchWord尾部&&字典树中下一个字符不存在&&没有被替换过 则尝试替换1个字符
            if (!replaced) {
                // 26 26个字母每个都尝试替换1次后继续往下走
                for (int i = 0; i < 26; i++) {
                    // i != idx 已知node.children[idx]为null；node.children[i] != null，找1个非null的子结点
                    if (i != idx && node.children[i] != null) {
                        // pos+1 深度优先子结点, true 被替换1次
                        if (dfs(searchWord, node.children[i], pos + 1, true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        class Trie {
            Trie[] children;
            boolean finished;

            public Trie() {
                this.children = new Trie[26];
                this.finished = false;
            }
        }
    }
}
