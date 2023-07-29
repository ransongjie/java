package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 064. 神奇的字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 * 实现 MagicDictionary 类：
 * - MagicDictionary() 初始化对象
 * - void buildDict(String[]dictionary) 使用字符串数组dictionary 设定该数据结构，dictionary
 * 中的字符串互不相同
 * - bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个
 * 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 * 
 * 注意
 * - 必须被替换1个字母
 */
public class Solution64 {
    /**
     * 蛮力法
     * 统计searchWord和dictionary数组中每个单词的相同位置不同字母的数量
     * 在dictionary中找到1个单词和seachword相差1个字母即可
     */
    class MagicDictionary1 {
        String[] dict;

        public MagicDictionary1() {
        }

        public void buildDict(String[] dictionary) {
            dict = dictionary;
        }

        public boolean search(String searchWord) {
            for (String str : dict) {
                // 剪枝
                if (searchWord.length() != str.length())
                    continue;

                int diff = 0;
                // 相同位置
                for (int i = 0; i < searchWord.length(); i++) {
                    // 不同单词数量
                    if (searchWord.charAt(i) != str.charAt(i))
                        diff++;
                    // 剪枝
                    if (diff > 1)
                        break;
                }

                // 在dictionary中找到1个单词和seachword相差1个字母即可
                if (diff == 1)
                    return true;
            }

            return false;
        }
    }

    /**
     * 字典树
     */
    class MagicDictionary2 {
        class Trie {
            Trie[] children;
            boolean finished;

            Trie() {
                children = new Trie[26];
                finished = false;
            }
        }

        Trie root;

        public MagicDictionary2() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                Trie node = root;
                for (char c : str.toCharArray()) {
                    int idx = c - 'a';
                    if (node.children[idx] == null) {
                        node.children[idx] = new Trie();
                    }
                    node = node.children[idx];
                }
                node.finished = true;
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord, root, 0, false);
        }

        /**
         * 
         * @param searchWord
         * @param node       Tire
         * @param pos        searchWord[pos]
         * @param replaced   true-被替换过1次
         * @return
         */
        private boolean dfs(String searchWord, Trie node, int pos, boolean replaced) {
            if (pos == searchWord.length()) {
                return replaced && node.finished;
            }

            int idx = searchWord.charAt(pos) - 'a';
            if (node.children[idx] != null) {
                if (dfs(searchWord, node.children[idx], pos + 1, replaced))
                    return true;
            }

            // 字母不存在，node.children[idx] = null
            // 字母存在但不满足要求，上面dfs返回false
            if (!replaced) {
                // 尝试替换pos位置字母，把26个字母都尝试1遍
                for (int i = 0; i < 26; i++) {
                    if (i != idx && node.children[i] != null) {
                        replaced = true;
                        if (dfs(searchWord, node.children[i], pos + 1, replaced))
                            return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }
}
