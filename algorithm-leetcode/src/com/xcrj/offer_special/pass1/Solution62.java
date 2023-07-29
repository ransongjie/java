package com.xcrj.offer_special.pass1;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * - Trie() 初始化前缀树对象。
 * - void insert(String word) 向前缀树中插入字符串 word 。
 * - boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * - boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Solution62 {

    /**
     * 字典树，一个结点包含26个指针，指向word的下一个子符
     */
    class Trie {
        // 字符串字符
        private Trie[] children;
        // 字符串结尾
        private boolean end;

        public Trie() {
            // 26：单词字母有26个
            this.children = new Trie[26];
            this.end = false;
        }

        /**
         * 构建字典树
         */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Trie node = this;
            for (char c : chars) {
                int idx = c - 'a';
                // 不存在这个字符则创建新对象
                if (null == node.children[idx]) {
                    node.children[idx] = new Trie();
                }
                // 移动到下一个字符
                node = node.children[idx];
            }
            // 字符串结束
            node.end = true;
        }

        /**
         * 查找是否存在字符串
         */
        public boolean search(String word) {
            Trie node = lastAccessNode(word);
            // 输入字符串和字典树中字符串一样长
            return null != node && node.end;
        }

        /**
         * 查找是否存在字符串前缀
         */
        public boolean startsWith(String prefix) {
            Trie node = lastAccessNode(prefix);
            // 输入字符串长度<=字典树中字符串
            return null != node;
        }

        /**
         * 公共方法，查找能够达到字典树的最后1个结点
         */
        private Trie lastAccessNode(String word) {
            Trie node = this;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                int idx = c - 'a';
                // 中途不可达返回null
                if (null == node.children[idx]) return null;
                node = node.children[idx];
            }
            return node;
        }
    }
}
