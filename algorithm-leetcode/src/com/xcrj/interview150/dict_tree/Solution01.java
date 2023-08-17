package com.xcrj.interview150.dict_tree;

/**
 * https://leetcode.cn/problems/implement-trie-prefix-tree
 * 实现 Trie (前缀树)
 */
public class Solution01 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        boolean flag = trie.startsWith("a");
        System.out.println(flag);
    }
}

class Trie {
    //！！！根结点。leetcode将多次 创建新的字典树。使用static会造成同根
//    static Trie root = new Trie();

    int pass;//经过
    int end;//结尾
    // 散列表 HashMap<Character,TireNode> nextMap;
    // 有序表 TreeMap<Character,TireNode> nextMap;
    Trie[] nexts;// 仅适用于全小写字母

    public Trie() {
        pass = 0;
        end = 0;
        nexts = new Trie[26];
    }

    //添加单词
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie p = this;
        p.pass++;//经过根结点1次
        char[] cs = word.toCharArray();
        int idx;
        for (char c : cs) {
            idx = c - 'a';
            //为空 则创建添加
            if (p.nexts[idx] == null) {
                p.nexts[idx] = new Trie();
            }
            p = p.nexts[idx];
            p.pass++;
        }
        p.end++;
    }

    //搜索单词
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        Trie p = this;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            p = p.nexts[c - 'a'];
            if (p == null) return false;
        }
        return p.end > 0 ? true : false;
    }

    //搜索前缀
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;
        Trie p = this;
        char[] cs = prefix.toCharArray();
        for (char c : cs) {
            p = p.nexts[c - 'a'];
            if (p == null) return false;
        }
        return p.pass > 0 ? true : false;
    }
}
