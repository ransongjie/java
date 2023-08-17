package com.xcrj.interview150.dict_tree;

/**
 * https://leetcode.cn/problems/design-add-and-search-words-data-structure
 * 添加与搜索单词 - 数据结构设计
 */
public class Solution02 {
}

class WordDictionary {
    int pass;//经过
    int end;//结尾
    // 散列表 HashMap<Character,TireNode> nextMap;
    // 有序表 TreeMap<Character,TireNode> nextMap;
    WordDictionary[] nexts;// 仅适用于全小写字母

    public WordDictionary() {
        pass = 0;
        end = 0;
        nexts = new WordDictionary[26];
    }

    //添加单词
    public void addWord(String word) {
        if (word == null || word.length() == 0) return;
        WordDictionary p = this;
        p.pass++;//经过根结点1次
        char[] cs = word.toCharArray();
        int idx;
        for (char c : cs) {
            idx = c - 'a';
            //为空 则创建添加
            if (p.nexts[idx] == null) {
                p.nexts[idx] = new WordDictionary();
            }
            p = p.nexts[idx];
            p.pass++;
        }
        p.end++;
    }

    //搜索单词
    public boolean search(String word) {
        return dfs(word, this, 0);
    }

    /**
     * @param s
     * @param p
     * @param i s[i]
     * @return
     */
    private boolean dfs(String s, WordDictionary p, int i) {
        int n = s.length();
        if (i == n) return p.end > 0 ? true : false;
        char c = s.charAt(i);
        // 当s[i]='.', 就有26种情况, 需要深度优先
        if (c == '.') {
            for (int j = 0; j < 26; j++) {
                if (p.nexts[j] != null && dfs(s, p.nexts[j], i + 1)) return true;
            }
            // p.nexts[26] 0~25都是null
            return false;
        } else {
            int idx = c - 'a';
            if (p.nexts[idx] == null) return false;
            return dfs(s, p.nexts[idx], i + 1);
        }
    }
}
